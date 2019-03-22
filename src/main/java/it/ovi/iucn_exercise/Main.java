package it.ovi.iucn_exercise;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.json.JsonObjectParser;
import it.ovi.iucn_exercise.models.*;
import it.ovi.iucn_exercise.urls.SpeciesByRegionUrl;
import it.ovi.iucn_exercise.urls.TokenUrl;
import it.ovi.iucn_exercise.urls.Urls;
import org.jetbrains.annotations.Contract;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;

public class Main {
    private static final String CRITICALLY_ENDANGERED_CATEGORY = "CR";
    private static final String MAMMAL_SPECIE = "MAMMALIA";

    private static final HttpRequestFactory REQUEST_FACTORY =
            HttpUtils.HTTP_TRANSPORT.createRequestFactory(
                    request -> request.setParser(new JsonObjectParser(HttpUtils.JSON_FACTORY)));

    public static void main(String[] argv) throws IOException {
        if (argv.length != 1) throw new RuntimeException("Pass one and only one argument: the token");
        String token = argv[0];

        // 1.
        TokenUrl regionListUrl = new TokenUrl(Urls.REGION_LIST_URL);
        regionListUrl.token = token;
        HttpRequest request = REQUEST_FACTORY.buildGetRequest(regionListUrl);
        RegionsJson regionsJson = request.execute().parseAs(RegionsJson.class);

        // 2.
        RegionJson regionJson = getRandomRegion(regionsJson);
        String regionIdentifier = regionJson.getIdentifier();

        // 3.
        SpeciesByRegionUrl speciesByRegionUrl = new SpeciesByRegionUrl(regionIdentifier, 0);
        speciesByRegionUrl.token = token;
        request = REQUEST_FACTORY.buildGetRequest(speciesByRegionUrl);
        SpeciesJson speciesJson = request.execute().parseAs(SpeciesJson.class);

        // 5.
        List<SpecieJson> specieFiltered = filterSpeciesByCriticallyEndangeredCategory(speciesJson.getResult());
        fetchConservationMeasureBYSpecie(specieFiltered, token);
        System.out.println(specieFiltered);

        // 6.
        List<SpecieJson> mammalSpecies = filterSpeciesByMammal(speciesJson.getResult());
        System.out.println(mammalSpecies);

        System.exit(0);
    }

    /**
     * Returns a random region in [regionsJsons].
     * @param regionsJson a list of regions.
     * @return a random region.
     */
    @Contract("null -> fail")
    private static RegionJson getRandomRegion(RegionsJson regionsJson) {
        if (regionsJson == null) throw new IllegalArgumentException("regionsJsons cannot be null");
        List<RegionJson> regionJsons = regionsJson.getResults();
        if (regionJsons.isEmpty()) throw new IllegalArgumentException("region list is empty");

        Random random = new Random();
        int regionChosenIndex = random.nextInt(regionJsons.size());

        return regionJsons.get(regionChosenIndex);
    }

    /**
     * Returns a list of species filtered by CR category.
     * @param species a list of species.
     * @return the filtered list.
     */
    private static List<SpecieJson> filterSpeciesByCriticallyEndangeredCategory(List<SpecieJson> species) {
        if (species == null) throw new IllegalArgumentException("species must not be null");

        List<SpecieJson> speciesFiltered = new ArrayList<>();

        for (SpecieJson specieJson : species) {
            if (Main.CRITICALLY_ENDANGERED_CATEGORY.equals(specieJson.getCategory())) {
                speciesFiltered.add(specieJson);
            }
        }

        return speciesFiltered;
    }

    /**
     * Fetches the conservation measure for the species and update each specie.
     * @param species a list of species.
     * @param token the IUCN token.
     */
    private static void fetchConservationMeasureBYSpecie(List<SpecieJson> species, String token) {
        // this should be cached thread pool, but IUCN crashes when it receives too much requests simultaneously.
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        List<Future<?>> futures = new ArrayList<>(species.size());
        Map<Long, ConservationMeasuresJson> conservationMeasuresJsonMap = new ConcurrentHashMap<>();
        for (SpecieJson specie : species) {
            futures.add(executorService.submit(
                    new FetchConservationMeasureTask(specie.getTaxonid(), conservationMeasuresJsonMap,
                            Main.REQUEST_FACTORY, token)));
        }

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }


        for (SpecieJson specie : species) {
            if (conservationMeasuresJsonMap.containsKey(specie.getTaxonid())) {
                specie.setConservationMeasureTitles(conservationMeasuresJsonMap.get(specie.getTaxonid()));
            }
        }

        executorService.shutdown();
    }

    /**
     * Filters species by mammal class.
     * @param species species
     * @return the filtered species.
     */
    private static List<SpecieJson> filterSpeciesByMammal(List<SpecieJson> species) {
        List<SpecieJson> filteredSpecies = new ArrayList<>();
        for (SpecieJson specie : species) {
            String className = specie.getClass_name();
            if (className != null && className.equals(MAMMAL_SPECIE)) {
                filteredSpecies.add(specie);
            }
        }

        return filteredSpecies;
    }
}
