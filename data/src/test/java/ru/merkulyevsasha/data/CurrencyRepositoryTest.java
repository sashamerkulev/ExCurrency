package ru.merkulyevsasha.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import ru.merkulyevsasha.core.models.Currency;
import ru.merkulyevsasha.data.db.CurrencyDatabase;
import ru.merkulyevsasha.data.network.CurrencyNetwork;
import ru.merkulyevsasha.data.network.models.CurrencyResponse;
import ru.merkulyevsasha.repositories.CurrencyRepository;

@RunWith(JUnit4.class)
public class CurrencyRepositoryTest {

    private final static String TEST_NUM = "num";
    private final static String TEST_CHR = "chr";
    private final static int TEST_NOMINAL = 10;
    private final static String TEST_NAME = "curName";
    private final static double TEST_VALUE = 13.6;

    private final CurrencyDatabase database = Mockito.mock(CurrencyDatabase.class);
    private final CurrencyNetwork network = Mockito.mock(CurrencyNetwork.class);
    private final CurrencyRepository repo = new CurrencyRepositoryImpl(database, network);

    private final List<CurrencyResponse> currencyResponses = new ArrayList<>();

    @Before
    public void initialTestData() {
        currencyResponses.add(getTestCurrencyResponse());
    }

    @After
    public void noMoreInteractions(){
        Mockito.verifyNoMoreInteractions(database, network);
    }

    @Test
    public void getCurrencyByNumCode() {
        Mockito.when(database.getCurrencyByNumCode(TEST_NUM)).thenReturn(new Currency(TEST_NUM, TEST_CHR, TEST_NOMINAL, TEST_NAME, TEST_VALUE));

        repo.getCurrencyByNumCode(TEST_NUM);

        Mockito.verify(database).getCurrencyByNumCode(TEST_NUM);
    }

    @Test
    public void getCurrencies_when_network_return_empty() throws IOException {
        Mockito.when(database.getCurrencies()).thenReturn(new ArrayList<Currency>());
        Mockito.when(network.getCurrencies()).thenReturn(new ArrayList<CurrencyResponse>());

        repo.getCurrencies();

        Mockito.verify(network).getCurrencies();
        Mockito.verify(database).getCurrencies();
    }

    @Test
    public void getCurrencies_when_network_return_given_data() throws IOException {
        Mockito.when(database.getCurrencies()).thenReturn(new ArrayList<Currency>());
        Mockito.when(network.getCurrencies()).thenReturn(currencyResponses);

        repo.getCurrencies();

        Mockito.verify(network).getCurrencies();
        Mockito.verify(database).addCurrencies(Mockito.<Currency>anyList()); // TODO matcher
    }

    private CurrencyResponse getTestCurrencyResponse() {
        CurrencyResponse response = new CurrencyResponse();
        response.chrCode = TEST_CHR;
        response.numCode = TEST_NUM;
        response.name = TEST_NAME;
        response.nomianal = TEST_NOMINAL;
        response.value = String.valueOf(TEST_VALUE);
        return response;
    }

}