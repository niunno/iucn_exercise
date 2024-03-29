package it.ovi.iucn_exercise.urls;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;

/**
 * A URL having a token as parameter.
 */
public class TokenUrl extends GenericUrl {

    public TokenUrl(String encodedUrl) {
        super(encodedUrl);
    }

    @Key public String token;
}
