package ru.merkulyevsasha.excurrency.data.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class CurrencyHttpClient {

    private final String currencyUrl;

    public CurrencyHttpClient(String url) {
        this.currencyUrl = url;
    }

    public String getRawData() throws IOException {
        URL url = new URL(currencyUrl);

        URLConnection connection = url.openConnection();
        connection.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "windows-1251"));
        StringBuilder buf = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            buf.append(line).append("\n");
        }

        return buf.toString();
    }
}
