package ru.merkulyevsasha.factories;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import ru.merkulyevsasha.data.CurrencyRepositoryImpl;
import ru.merkulyevsasha.data.db.CurrencyDatabase;
import ru.merkulyevsasha.data.db.CurrencyDatabaseImpl;
import ru.merkulyevsasha.data.network.CurrencyConverter;
import ru.merkulyevsasha.data.network.CurrencyHttpClient;
import ru.merkulyevsasha.data.network.CurrencyNetwork;
import ru.merkulyevsasha.data.network.CurrencyNetworkImpl;
import ru.merkulyevsasha.interactors.CurrencyInteractor;
import ru.merkulyevsasha.domain.CurrencyInteractorImpl;
import ru.merkulyevsasha.domain.calculator.CurrencyCalculatorImpl;
import ru.merkulyevsasha.domain.usecase.CurrencyConverterUsecaseImpl;

public class CurrencyFactory {

    private final CurrencyInteractor interactor;

    public CurrencyFactory(Context context) {
        CurrencyDatabase database = new CurrencyDatabaseImpl(context);
        CurrencyNetwork network = new CurrencyNetworkImpl(new CurrencyHttpClient(BuildConfig.CURRENCY_URL), new CurrencyConverter());
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        interactor = new CurrencyInteractorImpl(
            executorService,
            new CurrencyRepositoryImpl(database, network),
            new CurrencyConverterUsecaseImpl(new CurrencyCalculatorImpl())
        );
    }

    public CurrencyInteractor getCurrencyInteractor() {
        return interactor;
    }
}
