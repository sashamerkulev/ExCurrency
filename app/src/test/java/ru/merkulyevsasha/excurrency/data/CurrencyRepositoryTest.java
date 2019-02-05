package ru.merkulyevsasha.excurrency.data;

import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import ru.merkulyevsasha.excurrency.data.db.CurrencyDatabase;
import ru.merkulyevsasha.excurrency.domain.Currency;

@RunWith(JUnit4.class)
public class CurrencyRepositoryTest {

    private final static String TEST_NUM = "num";
    private final static String TEST_CHR = "chr";
    private final static int TEST_NOMINAL = 10;
    private final static String TEST_NAME = "curName";
    private final static double TEST_VALUE = 13.6;

    private final CurrencyDatabase database = Mockito.mock(CurrencyDatabase.class);
    private final CurrencyRepository repo = new CurrencyRepositoryImpl(database);

    @Test
    public void getCurrencyByNumCode() {
        Mockito.when(database.getCurrencyByNumCode(TEST_NUM)).thenReturn(new Currency(TEST_NUM, TEST_CHR, TEST_NOMINAL, TEST_NAME, TEST_VALUE));

        repo.getCurrencyByNumCode(TEST_NUM);

        Mockito.verify(database).getCurrencyByNumCode(TEST_NUM);
    }

    @Test
    public void getCurrencies() {
        Mockito.when(database.getCurrencies()).thenReturn(new ArrayList<Currency>());

        repo.getCurrencies();

        Mockito.verify(database).getCurrencies();
    }
}