package it.ovi.iucn_exercise;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.json.JsonObjectParser;
import it.ovi.iucn_exercise.models.RegionJson;
import it.ovi.iucn_exercise.models.RegionsJson;
import it.ovi.iucn_exercise.models.SpecieJson;
import it.ovi.iucn_exercise.models.SpeciesJson;
import it.ovi.iucn_exercise.urls.SpeciesByRegionUrl;
import it.ovi.iucn_exercise.urls.TokenUrl;
import it.ovi.iucn_exercise.urls.Urls;
import org.jetbrains.annotations.Contract;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static final String CRITICALLY_ENDANGERED_CATEGORY = "CR";

    public static void main(String[] argv) throws IOException {
        if (argv.length != 1) throw new RuntimeException("Pass one and only one argument: the token");
        String token = argv[0];

        HttpRequestFactory requestFactory =
                HttpUtils.HTTP_TRANSPORT.createRequestFactory(
                        request -> request.setParser(new JsonObjectParser(HttpUtils.JSON_FACTORY)));
        TokenUrl regionListUrl = new TokenUrl(Urls.REGION_LIST_URL);
        regionListUrl.token = token;
        HttpRequest request = requestFactory.buildGetRequest(regionListUrl);
        RegionsJson regionsJson = request.execute().parseAs(RegionsJson.class);

        RegionJson regionJson = getRandomRegion(regionsJson);
        String regionIdentifier = regionJson.getIdentifier();

        SpeciesByRegionUrl speciesByRegionUrl = new SpeciesByRegionUrl(regionIdentifier, 0);
        speciesByRegionUrl.token = token;
        request = requestFactory.buildGetRequest(speciesByRegionUrl);
        SpeciesJson speciesJson = request.execute().parseAs(SpeciesJson.class);
        List<SpecieJson> specieFiltered = filterSpeciesByCategory(speciesJson.getResult(), CRITICALLY_ENDANGERED_CATEGORY);

        System.out.print(specieFiltered);
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
     * Returns a list of species filtered by a category (CR).
     * @param species a list of species.
     * @param category a category used to filter them.
     * @return the filtered list.
     */
    @Contract("null, _ -> fail; !null, null -> fail")
    private static List<SpecieJson> filterSpeciesByCategory(List<SpecieJson> species, String category) {
        if (species == null) throw new IllegalArgumentException("species must not be null");
        if (category == null) throw new IllegalArgumentException("category must not be null");

        List<SpecieJson> speciesFiltered = new ArrayList<>();

        for (SpecieJson specieJson : species) {
            if (category.equals(specieJson.getCategory())) {
                speciesFiltered.add(specieJson);
            }
        }

        return speciesFiltered;
    }
}
