package ru.merkulyevsasha.data.network.mappers;

import ru.merkulyevsasha.core.models.Currency;
import ru.merkulyevsasha.data.network.models.CurrencyResponse;

public class CurrencyResponseMapper implements Mapper<CurrencyResponse, Currency> {
    @Override
    public Currency map(CurrencyResponse item) {
        double value = Double.parseDouble(item.value.replace(",", "."));
        return new Currency(item.numCode, item.chrCode, item.nomianal, item.name, value);
    }
}
