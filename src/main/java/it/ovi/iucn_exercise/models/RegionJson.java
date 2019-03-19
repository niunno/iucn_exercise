package it.ovi.iucn_exercise.models;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class RegionJson extends GenericJson {
    @Key private String name;
    @Key private String identifier;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "RegionJson{" +
                "name='" + name + '\'' +
                ", identifier='" + identifier + '\'' +
                '}';
    }
}
