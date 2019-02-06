package ru.merkulyevsasha.excurrency.domain;

import java.util.List;
import ru.merkulyevsasha.excurrency.domain.models.Currency;

public interface GetCurrenciesCalback {

    void onSuccess(List<Currency> currencies);
    void onFailure();

}
