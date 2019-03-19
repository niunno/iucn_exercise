package it.ovi.iucn_exercise;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

public class HttpUtils {
    public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    public static final JacksonFactory JSON_FACTORY = new JacksonFactory();
}
