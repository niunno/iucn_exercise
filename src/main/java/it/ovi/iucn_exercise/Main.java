package it.ovi.iucn_exercise;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.json.JsonObjectParser;
import it.ovi.iucn_exercise.models.RegionJson;
import it.ovi.iucn_exercise.models.RegionsJson;
import it.ovi.iucn_exercise.urls.TokenUrl;
import it.ovi.iucn_exercise.urls.Urls;
import org.jetbrains.annotations.Contract;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] argv) throws IOException {
        if (argv.length != 1) throw new RuntimeException("Pass one and only one argument: the token");
        String token = argv[0];

        HttpRequestFactory requestFactory =
                HttpUtils.HTTP_TRANSPORT.createRequestFactory(
                        request -> request.setParser(new JsonObjectParser(HttpUtils.JSON_FACTORY)));
        TokenUrl regionListUrl = new TokenUrl(Urls.REGION_LIST_URL);
        regionListUrl.token = token;
        final HttpRequest request = requestFactory.buildGetRequest(regionListUrl);
        final RegionsJson regionsJson = request.execute().parseAs(RegionsJson.class);

        RegionJson regionJson = getRandomRegion(regionsJson);

        System.out.print(regionJson);
    }

    @Contract("null -> fail")
    private static RegionJson getRandomRegion(RegionsJson regionsJson) {
        if (regionsJson == null) throw new IllegalArgumentException("regionsJsons cannot be null");
        List<RegionJson> regionJsons = regionsJson.getResults();
        if (regionJsons.isEmpty()) throw new IllegalArgumentException("region list is empty");

        Random random = new Random();
        int regionChosenIndex = random.nextInt(regionJsons.size());

        return regionJsons.get(regionChosenIndex);
    }
}
