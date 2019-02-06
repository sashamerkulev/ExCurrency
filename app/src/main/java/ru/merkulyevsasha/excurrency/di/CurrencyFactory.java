package ru.merkulyevsasha.excurrency.di;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import ru.merkulyevsasha.excurrency.BuildConfig;
import ru.merkulyevsasha.excurrency.data.CurrencyRepositoryImpl;
import ru.merkulyevsasha.excurrency.data.db.CurrencyDatabase;
import ru.merkulyevsasha.excurrency.data.db.CurrencyDatabaseImpl;
import ru.merkulyevsasha.excurrency.data.network.CurrencyConverter;
import ru.merkulyevsasha.excurrency.data.network.CurrencyHttpClient;
import ru.merkulyevsasha.excurrency.data.network.CurrencyNetwork;
import ru.merkulyevsasha.excurrency.data.network.CurrencyNetworkImpl;
import ru.merkulyevsasha.excurrency.domain.CurrencyInteractor;
import ru.merkulyevsasha.excurrency.domain.CurrencyInteractorImpl;
import ru.merkulyevsasha.excurrency.domain.calculator.CurrencyCalculatorImpl;

public class CurrencyFactory {

    private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private final CurrencyDatabase database;
    private final CurrencyNetwork network;
    private final CurrencyInteractor interactor;

    public CurrencyFactory(Context context) {
        database = new CurrencyDatabaseImpl(context);
        network = new CurrencyNetworkImpl(new CurrencyHttpClient(BuildConfig.CURRENCY_URL), new CurrencyConverter());
        interactor = new CurrencyInteractorImpl(
            executorService,
            new CurrencyRepositoryImpl(database, network),
            new CurrencyCalculatorImpl()
        );
    }

    public CurrencyInteractor getCurrencyInteractor() {
        return interactor;
    }
}
