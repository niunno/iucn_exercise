package it.ovi.iucn_exercise.models;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

import java.util.List;

/**
 * Conservation measure list from IUCN APIs.
 */
public class ConservationMeasuresJson extends GenericJson {
    @Key
    private long id;
    @Key
    private String region_identifier;
    @Key
    private List<ConservationMeasureJson> result;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRegion_identifier() {
        return region_identifier;
    }

    public void setRegion_identifier(String region_identifier) {
        this.region_identifier = region_identifier;
    }

    public List<ConservationMeasureJson> getResult() {
        return result;
    }

    public void setResult(List<ConservationMeasureJson> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ConservationMeasuresJson{" +
                "id=" + id +
                ", region_identifier='" + region_identifier + '\'' +
                ", result=" + result +
                '}';
    }
}
