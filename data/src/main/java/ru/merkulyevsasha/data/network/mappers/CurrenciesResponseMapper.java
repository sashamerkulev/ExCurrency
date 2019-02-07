package ru.merkulyevsasha.data.network.mappers;

import java.util.ArrayList;
import java.util.List;
import ru.merkulyevsasha.core.models.Currency;
import ru.merkulyevsasha.data.network.models.CurrencyResponse;

public class CurrenciesResponseMapper implements Mapper<List<CurrencyResponse>, List<Currency>> {

    private final CurrencyResponseMapper mapper = new CurrencyResponseMapper();

    @Override
    public List<Currency> map(List<CurrencyResponse> items) {
        List<Currency> result = new ArrayList<>();
        for (CurrencyResponse item : items) {
            result.add(mapper.map(item));
        }
        return result;
    }
}
