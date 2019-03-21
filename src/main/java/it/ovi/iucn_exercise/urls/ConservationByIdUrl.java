package it.ovi.iucn_exercise.urls;

/**
 * The URL of conservation by specie ID.
 */
public class ConservationByIdUrl extends TokenUrl {
    /**
     * Init the object.
     * @param specieId specie ID.
     * @param regionId region ID.
     */
    public ConservationByIdUrl(String specieId, String regionId) {
        super(Urls.getConservationByIdUrl(specieId, regionId));
    }
}
