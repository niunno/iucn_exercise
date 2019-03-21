package it.ovi.iucn_exercise.urls;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Urls {
    public static final String REGION_LIST_URL = "http://apiv3.iucnredlist.org/api/v3/region/list";

    private static final String REGION_ID_PLACEHOLDER = ":region_identifier";
    private static final String PAGE_PLACEHOLDER = ":page_number";
    private static final String SPECIE_ID_PLACEHOLDER = ":id";

    private static final String SPECIES_PER_REGION_URL =
            String.format("http://apiv3.iucnredlist.org/api/v3/species/region/%s/page/%s",
                    REGION_ID_PLACEHOLDER, PAGE_PLACEHOLDER);
    private static final String CONSERVATION_BY_ID_URL =
            String.format("http://apiv3.iucnredlist.org/api/v3/measures/species/id/%s/region/%s?token='YOUR TOKEN'",
                    SPECIE_ID_PLACEHOLDER, REGION_ID_PLACEHOLDER);

    /**
     * Returns the Species-by-region URL.
     * @param region the region identifier.
     * @param page the page number (must be >= 0).
     * @return the URL.
     */
    @NotNull
    @Contract("null, _ -> fail")
    public static String getSpeciesByRegionUrl(String region, int page) {
        if (region == null) throw new IllegalArgumentException("region cannot be null");
        if (page < 0) throw new IllegalArgumentException("page cannot be less then 0");

        return SPECIES_PER_REGION_URL
                .replace(REGION_ID_PLACEHOLDER, region)
                .replace(PAGE_PLACEHOLDER, Integer.toString(page));
    }

    /**
     * Returns the conservation by ID URL.
     * @param specieId the specie ID.
     * @param regionId the region ID.
     * @return the URL.
     */
    @NotNull
    @Contract("null, _ -> fail; !null, null -> fail")
    public static String getConservationByIdUrl(String specieId, String regionId) {
        if (specieId == null) throw new IllegalArgumentException("specieId must not be null");
        if (regionId == null) throw new IllegalArgumentException("regionId must not be null");

        return CONSERVATION_BY_ID_URL
                .replace(SPECIE_ID_PLACEHOLDER, specieId)
                .replace(REGION_ID_PLACEHOLDER, regionId);
    }
}
