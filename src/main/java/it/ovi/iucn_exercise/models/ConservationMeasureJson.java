package it.ovi.iucn_exercise.models;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/**
 * Conservation Measure JSON from IUCN.
 */
public class ConservationMeasureJson extends GenericJson {
    @Key
    private String code;
    @Key
    private String title;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ConservationMeasureJson{" +
                "code='" + code + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
