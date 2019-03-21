package it.ovi.iucn_exercise.urls;

/**
 * The Species-by-region URL.
 */
public class SpeciesByRegionUrl extends TokenUrl {
    /**
     * Init the object.
     * @param region the region identifier.
     * @param page the page number (must be >=0).
     */
    public SpeciesByRegionUrl(String region, int page) {
        super(Urls.getSpeciesByRegionUrl(region, page));
    }
}
