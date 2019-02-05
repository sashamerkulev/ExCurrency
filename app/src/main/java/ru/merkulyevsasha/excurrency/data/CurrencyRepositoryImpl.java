package ru.merkulyevsasha.excurrency.data;

import android.support.annotation.NonNull;
import java.util.List;
import ru.merkulyevsasha.excurrency.data.db.CurrencyDatabase;
import ru.merkulyevsasha.excurrency.domain.Currency;

public class CurrencyRepositoryImpl implements CurrencyRepository{

    private final CurrencyDatabase database;

    public CurrencyRepositoryImpl(CurrencyDatabase database) {
        this.database = database;
    }

    @Override
    public Currency getCurrencyByNumCode(@NonNull String numCode) {
        return database.getCurrencyByNumCode(numCode);
    }

    @Override
    public List<Currency> getCurrencies() {
        return database.getCurrencies();
    }
}
