package it.ovi.iucn_exercise.models;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

import java.util.List;

/**
 * The JSON Region list by IUCN APIs.
 */
public class RegionsJson extends GenericJson {
    @Key private int count;
    @Key private List<RegionJson> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<RegionJson> getResults() {
        return results;
    }

    public void setResults(List<RegionJson> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "RegionsJson{" +
                "count=" + count +
                ", results=" + results +
                '}';
    }
}
