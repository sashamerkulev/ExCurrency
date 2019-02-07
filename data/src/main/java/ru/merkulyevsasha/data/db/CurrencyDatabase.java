package ru.merkulyevsasha.data.db;

import android.support.annotation.NonNull;
import java.util.List;
import ru.merkulyevsasha.core.models.Currency;

public interface CurrencyDatabase {

    Currency getCurrencyByNumCode(@NonNull String numCode);

    void deleteCurrencies();

    void addCurrencies(@NonNull List<Currency> currencies);

    List<Currency> getCurrencies();

    void close();
}
