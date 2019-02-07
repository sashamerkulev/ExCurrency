package ru.merkulyevsasha.repositories;

import android.support.annotation.NonNull;
import java.util.List;
import ru.merkulyevsasha.core.models.Currency;

public interface CurrencyRepository {
    Currency getCurrencyByNumCode(@NonNull String numCode);

    List<Currency> getCurrencies();
}
