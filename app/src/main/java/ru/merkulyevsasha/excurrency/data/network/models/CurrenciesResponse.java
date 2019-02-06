package ru.merkulyevsasha.excurrency.data.network.models;

import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "ValCurs")
public class CurrenciesResponse {
    @ElementList(inline = true)
    public List<CurrencyResponse> currencies;
}
