package ru.merkulyevsasha.data.network.mappers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.merkulyevsasha.core.models.Currency;
import ru.merkulyevsasha.data.network.models.CurrencyResponse;

@RunWith(JUnit4.class)
public class CurrencyResponseMapperTest {

    private final static String TEST_NUM = "num";
    private final static String TEST_CHR = "chr";
    private final static int TEST_NOMINAL = 10;
    private final static String TEST_NAME = "curName";

    private final CurrencyResponseMapper mapper = new CurrencyResponseMapper();

    @Test
    public void map_returns_given_data_with_comma_value() {

        Currency result = mapper.map(getTestCurrencyResponse("31,57"));

        Assert.assertEquals(TEST_NUM, result.getNumCode());
        Assert.assertEquals(TEST_CHR, result.getChrCode());
        Assert.assertEquals(TEST_NAME, result.getName());
        Assert.assertEquals(TEST_NOMINAL, result.getNomianal());
        Assert.assertEquals(31.57, result.getValue(), 0);
    }

    @Test
    public void map_returns_given_data_with_dot_value() {

        Currency result = mapper.map(getTestCurrencyResponse("111.66"));

        Assert.assertEquals(TEST_NUM, result.getNumCode());
        Assert.assertEquals(TEST_CHR, result.getChrCode());
        Assert.assertEquals(TEST_NAME, result.getName());
        Assert.assertEquals(TEST_NOMINAL, result.getNomianal());
        Assert.assertEquals(111.66, result.getValue(), 0);
    }

    private CurrencyResponse getTestCurrencyResponse(String value) {
        CurrencyResponse response = new CurrencyResponse();
        response.chrCode = TEST_CHR;
        response.numCode = TEST_NUM;
        response.name = TEST_NAME;
        response.nomianal = TEST_NOMINAL;
        response.value = value;
        return response;
    }
}