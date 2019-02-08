package ru.merkulyevsasha.data.db;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.merkulyevsasha.core.models.Currency;

@RunWith(AndroidJUnit4.class)
public class CurrencyDatabaseTest {

    private CurrencyDatabase db;

    private final static String TEST_NUM = "num";
    private final static String TEST_CHR = "chr";
    private final static int TEST_NOMINAL = 10;
    private final static String TEST_NAME = "curName";
    private final static double TEST_VALUE = 13.6;

    @Before
    public void setUp() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        db = new CurrencyDatabaseImpl(appContext);
        db.deleteCurrencies();
    }

    @After
    public void tearDown() {
        db.close();
    }

    @Test
    public void getCurrencyByNumCode_returns_given_data_if_it_exists() {
        addTestCurrencies();

        Currency resultCurrency = db.getCurrencyByNumCode(TEST_CHR);

        Assert.assertEquals(TEST_NUM, resultCurrency.getNumCode());
        Assert.assertEquals(TEST_CHR, resultCurrency.getChrCode());
        Assert.assertEquals(TEST_NAME, resultCurrency.getName());
        Assert.assertEquals(TEST_NOMINAL, resultCurrency.getNomianal());
        Assert.assertEquals(TEST_VALUE, resultCurrency.getValue(), 0);
    }

    @Test
    public void getCurrencyByNumCode_returns_null_if_it_does_not_exist() {
        Currency currency = db.getCurrencyByNumCode(TEST_NUM);
        Assert.assertNull(currency);
    }

    @Test
    public void deleteCurrencies_delets_all_data() {
        db.deleteCurrencies();

        List<Currency> resultCurrencies = db.getCurrencies();

        Assert.assertEquals(0, resultCurrencies.size());
    }

    @Test
    public void addCurrencies_adds_actual_data() {
        addTestCurrencies();

        List<Currency> resultCurrencies = db.getCurrencies();

        Assert.assertEquals(1, resultCurrencies.size());
    }

    @Test
    public void getCurrencies_returns_existing_data() {
        addTestCurrencies();

        List<Currency> resultCurrencies = db.getCurrencies();

        Assert.assertEquals(1, resultCurrencies.size());
    }

    @Test
    public void getCurrencies_returns_empty_list_if_no_data() {
        db.deleteCurrencies();

        List<Currency> resultCurrencies = db.getCurrencies();

        Assert.assertEquals(0, resultCurrencies.size());
    }

    private void addTestCurrencies() {
        Currency currency = new Currency(TEST_NUM, TEST_CHR, TEST_NOMINAL, TEST_NAME, TEST_VALUE);
        List<Currency> currencies = new ArrayList<>();
        currencies.add(currency);
        db.addCurrencies(currencies);
    }
}