package ru.merkulyevsasha.interactors;

import java.util.List;
import ru.merkulyevsasha.core.models.Currency;

public interface GetCurrenciesCalback {

    void onSuccess(List<Currency> currencies);
    void onFailure();

}
