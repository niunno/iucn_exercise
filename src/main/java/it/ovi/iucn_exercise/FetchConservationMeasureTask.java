package it.ovi.iucn_exercise;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import it.ovi.iucn_exercise.models.ConservationMeasuresJson;
import it.ovi.iucn_exercise.urls.ConservationByIdUrl;

import java.io.IOException;
import java.util.Map;

/**
 * Class that helps fetching the IUCN conservation measure for a specie
 */
public class FetchConservationMeasureTask implements Runnable {
    private long mSpecieId;
    private Map<Long, ConservationMeasuresJson> mConservationMeasureKeeper;
    private HttpRequestFactory mHttpRequestFactory;
    private String mToken;

    /**
     * Init the object
     * @param specieId specie ID.
     * @param conservationMeasureKeeper where the conservation measure data for the specie will be loaded.
     * @param httpRequestFactory an http request factory.
     */
    public FetchConservationMeasureTask(long specieId, Map<Long,
            ConservationMeasuresJson> conservationMeasureKeeper, HttpRequestFactory httpRequestFactory,
                                        String token) {
        mSpecieId = specieId;
        mConservationMeasureKeeper = conservationMeasureKeeper;
        mHttpRequestFactory = httpRequestFactory;
        mToken = token;
    }

    /**
     * Fetches the conservation measure for the specie.
     */
    @Override
    public void run() {
        ConservationByIdUrl url = new ConservationByIdUrl(mSpecieId);
        url.token = mToken;

        HttpRequest httpRequest;
        try {
            httpRequest = mHttpRequestFactory.buildGetRequest(url);
        } catch (IOException e) {
            throw new RuntimeException("Error when creating HTTP request", e);
        }

        ConservationMeasuresJson conservationMeasuresJson;
        try {
            conservationMeasuresJson = httpRequest.execute().parseAs(ConservationMeasuresJson.class);
        } catch (IOException e) {
            throw new RuntimeException("Error when execution http request");
        }

        mConservationMeasureKeeper.put(mSpecieId, conservationMeasuresJson);
    }
}
