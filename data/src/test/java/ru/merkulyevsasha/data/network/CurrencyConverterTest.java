package ru.merkulyevsasha.data.network;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.merkulyevsasha.data.network.models.CurrencyResponse;

@RunWith(JUnit4.class)
public class CurrencyConverterTest {

    @Test
    public void convert_converts_given_xml_string() {

        String xml = "<?xml version=\"1.0\" encoding=\"windows-1251\"?>\n"
            + "<ValCurs Date=\"06.02.2019\" name=\"Foreign Currency Market\">\n"
            + "  <Valute ID=\"R01010\">\n"
            + "    <NumCode>036</NumCode>\n"
            + "\t<CharCode>AUD</CharCode>\n"
            + "\t<Nominal>1</Nominal>\n"
            + "\t<Name>Австралийский доллар</Name>\n"
            + "\t<Value>47,5179</Value></Valute>\n"
            + "</ValCurs>";

        List<CurrencyResponse> result = new CurrencyConverter().convert(xml);
        Assert.assertEquals(1, result.size());
    }
}