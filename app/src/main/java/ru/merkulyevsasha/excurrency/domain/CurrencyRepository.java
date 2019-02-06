package ru.merkulyevsasha.excurrency.domain;

import android.support.annotation.NonNull;
import java.util.List;
import ru.merkulyevsasha.excurrency.domain.models.Currency;

public interface CurrencyRepository {
    Currency getCurrencyByNumCode(@NonNull String numCode);

    List<Currency> getCurrencies();
}
