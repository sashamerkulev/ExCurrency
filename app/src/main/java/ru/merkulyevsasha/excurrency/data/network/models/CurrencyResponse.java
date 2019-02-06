package ru.merkulyevsasha.excurrency.data.network.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Valute")
public class CurrencyResponse {

    @Element(name = "NumCode")
    public String numCode;

    @Element(name = "CharCode")
    public String chrCode;

    @Element(name = "Nominal")
    public int nomianal;

    @Element(name = "Name")
    public String name;

    @Element(name = "Value")
    public String value;
}
