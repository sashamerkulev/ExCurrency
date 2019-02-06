package ru.merkulyevsasha.excurrency.data.network;

import java.io.IOException;
import java.util.List;
import ru.merkulyevsasha.excurrency.data.network.models.CurrencyResponse;

public interface CurrencyNetwork {

    List<CurrencyResponse> getCurrencies() throws IOException;
}
