package ru.merkulyevsasha.data.network;

import java.io.IOException;
import java.util.List;
import ru.merkulyevsasha.data.network.models.CurrencyResponse;

public interface CurrencyNetwork {

    List<CurrencyResponse> getCurrencies() throws IOException;
}
