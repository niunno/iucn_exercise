package it.ovi.iucn_exercise.models;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/**
 * The Specie JSON from IUCN APIs.
 */
public class SpecieJson extends GenericJson {
    @Key
    private int taxonid;
    @Key
    private String kingdom_name;
    @Key
    private String phylum_name;
    @Key
    private String class_name;
    @Key
    private String order_name;
    @Key
    private String family_name;
    @Key
    private String genus_name;
    @Key
    private String scientific_name;
    @Key
    private String infra_rank;
    @Key
    private String infra_name;
    @Key
    private String population;
    @Key
    private String category;

    public int getTaxonid() {
        return taxonid;
    }

    public void setTaxonid(int taxonid) {
        this.taxonid = taxonid;
    }

    public String getKingdom_name() {
        return kingdom_name;
    }

    public void setKingdom_name(String kingdom_name) {
        this.kingdom_name = kingdom_name;
    }

    public String getPhylum_name() {
        return phylum_name;
    }

    public void setPhylum_name(String phylum_name) {
        this.phylum_name = phylum_name;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public String getGenus_name() {
        return genus_name;
    }

    public void setGenus_name(String genus_name) {
        this.genus_name = genus_name;
    }

    public String getScientific_name() {
        return scientific_name;
    }

    public void setScientific_name(String scientific_name) {
        this.scientific_name = scientific_name;
    }

    public String getInfra_rank() {
        return infra_rank;
    }

    public void setInfra_rank(String infra_rank) {
        this.infra_rank = infra_rank;
    }

    public String getInfra_name() {
        return infra_name;
    }

    public void setInfra_name(String infra_name) {
        this.infra_name = infra_name;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "SpecieJson{" +
                "taxonid=" + taxonid +
                ", kingdom_name='" + kingdom_name + '\'' +
                ", phylum_name='" + phylum_name + '\'' +
                ", class_name='" + class_name + '\'' +
                ", order_name='" + order_name + '\'' +
                ", family_name='" + family_name + '\'' +
                ", genus_name='" + genus_name + '\'' +
                ", scientific_name='" + scientific_name + '\'' +
                ", infra_rank='" + infra_rank + '\'' +
                ", infra_name='" + infra_name + '\'' +
                ", population='" + population + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}