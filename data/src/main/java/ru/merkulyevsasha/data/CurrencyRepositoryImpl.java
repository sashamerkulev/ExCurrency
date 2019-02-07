package ru.merkulyevsasha.data;

import android.support.annotation.NonNull;
import java.io.IOException;
import java.util.List;
import ru.merkulyevsasha.repositories.CurrencyRepository;
import ru.merkulyevsasha.core.models.Currency;
import ru.merkulyevsasha.data.db.CurrencyDatabase;
import ru.merkulyevsasha.data.network.CurrencyNetwork;
import ru.merkulyevsasha.data.network.mappers.CurrenciesResponseMapper;
import ru.merkulyevsasha.data.network.models.CurrencyResponse;

public class CurrencyRepositoryImpl implements CurrencyRepository {

    private final CurrencyDatabase database;
    private final CurrencyNetwork network;
    private final CurrenciesResponseMapper mapper = new CurrenciesResponseMapper();

    public CurrencyRepositoryImpl(CurrencyDatabase database, CurrencyNetwork network) {
        this.database = database;
        this.network = network;
    }

    @Override
    public Currency getCurrencyByNumCode(@NonNull String numCode) {
        return database.getCurrencyByNumCode(numCode);
    }

    @Override
    public List<Currency> getCurrencies() {
        try {
            List<CurrencyResponse> currenciesResponse = network.getCurrencies();
            if (currenciesResponse.size() > 0) {
                List<Currency> currencies = mapper.map(currenciesResponse);
                database.deleteCurrencies();
                database.addCurrencies(currencies);
                return currencies;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return database.getCurrencies();
    }
}
