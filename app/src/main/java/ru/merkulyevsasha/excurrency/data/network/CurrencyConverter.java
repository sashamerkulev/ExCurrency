package ru.merkulyevsasha.excurrency.data.network;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.core.Persister;
import ru.merkulyevsasha.excurrency.data.network.models.CurrenciesResponse;
import ru.merkulyevsasha.excurrency.data.network.models.CurrencyResponse;

public class CurrencyConverter {

    public List<CurrencyResponse> convert(String xml) {
        Reader reader = new StringReader(xml);
        Persister serializer = new Persister();
        CurrenciesResponse currencies = null;
        try {
            currencies = serializer.read(CurrenciesResponse.class, reader, false);
            return currencies.currencies;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
