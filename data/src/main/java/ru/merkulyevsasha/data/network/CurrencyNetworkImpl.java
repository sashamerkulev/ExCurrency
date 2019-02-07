package ru.merkulyevsasha.data.network;

import java.io.IOException;
import java.util.List;
import ru.merkulyevsasha.data.network.models.CurrencyResponse;

public class CurrencyNetworkImpl implements CurrencyNetwork {

    private final CurrencyHttpClient client;
    private final CurrencyConverter converter;

    public CurrencyNetworkImpl(CurrencyHttpClient client, CurrencyConverter converter) {
        this.client = client;
        this.converter = converter;
    }

    @Override
    public List<CurrencyResponse> getCurrencies() throws IOException {
        return converter.convert(client.getRawData());
    }
}
