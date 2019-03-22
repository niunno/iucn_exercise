package it.ovi.iucn_exercise.urls;

/**
 * The URL of conservation by specie ID.
 */
public class ConservationByIdUrl extends TokenUrl {
    /**
     * Init the object.
     * @param specieId specie ID.
     */
    public ConservationByIdUrl(long specieId) {
        super(Urls.getConservationByIdUrl(specieId));
    }
}
