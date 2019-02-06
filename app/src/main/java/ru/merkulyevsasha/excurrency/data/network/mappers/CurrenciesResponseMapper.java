package ru.merkulyevsasha.excurrency.data.network.mappers;

import java.util.ArrayList;
import java.util.List;
import ru.merkulyevsasha.excurrency.data.network.models.CurrencyResponse;
import ru.merkulyevsasha.excurrency.domain.models.Currency;

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
