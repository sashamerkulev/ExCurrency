package ru.merkulyevsasha.excurrency.domain.usecase;

import ru.merkulyevsasha.excurrency.domain.ConvertCurrencyCallback;
import ru.merkulyevsasha.excurrency.domain.models.Currency;

public interface CurrencyConverterUsecase {
    void convert(double value, String curFrom, String curTo,
        Currency from, Currency to, ConvertCurrencyCallback callback);
}
