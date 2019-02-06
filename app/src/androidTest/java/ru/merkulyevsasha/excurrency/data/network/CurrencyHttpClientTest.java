package ru.merkulyevsasha.excurrency.data.network;

import android.support.test.runner.AndroidJUnit4;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class CurrencyHttpClientTest {

    @Test
    public void getRawData_works_if_returns_non_empty_string() throws IOException {
        CurrencyHttpClient client = new CurrencyHttpClient("http://www.cbr.ru/scripts/XML_daily.asp");
        String result = client.getRawData();
        Assert.assertFalse(result.isEmpty());
    }
}