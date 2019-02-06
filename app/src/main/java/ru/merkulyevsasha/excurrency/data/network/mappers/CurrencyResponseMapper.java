package ru.merkulyevsasha.excurrency.data.network.mappers;

import ru.merkulyevsasha.excurrency.data.network.models.CurrencyResponse;
import ru.merkulyevsasha.excurrency.domain.Currency;

public class CurrencyResponseMapper implements Mapper<CurrencyResponse, Currency> {
    @Override
    public Currency map(CurrencyResponse item) {
        double value = Double.parseDouble(item.value.replace(",", "."));
        return new Currency(item.numCode, item.chrCode, item.nomianal, item.name, value);
    }
}
