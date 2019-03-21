package it.ovi.iucn_exercise.models;

import com.google.api.client.util.Key;

import java.util.List;

/**
 * The JSON Species list by IUCN APIs.
 */
public class SpeciesJson {
    @Key
    private int count;
    @Key
    private String region_identifier;
    @Key
    private int page;
    @Key
    private List<SpecieJson> result;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getRegion_identifier() {
        return region_identifier;
    }

    public void setRegion_identifier(String region_identifier) {
        this.region_identifier = region_identifier;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<SpecieJson> getResult() {
        return result;
    }

    public void setResult(List<SpecieJson> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "SpeciesJson{" +
                "count=" + count +
                ", region_identifier='" + region_identifier + '\'' +
                ", page=" + page +
                ", result=" + result +
                '}';
    }
}
