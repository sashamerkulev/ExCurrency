package ru.merkulyevsasha.excurrency.data;

import android.support.annotation.NonNull;
import java.util.List;
import ru.merkulyevsasha.excurrency.domain.Currency;

public interface CurrencyRepository {
    Currency getCurrencyByNumCode(@NonNull String numCode);

    List<Currency> getCurrencies();
}
