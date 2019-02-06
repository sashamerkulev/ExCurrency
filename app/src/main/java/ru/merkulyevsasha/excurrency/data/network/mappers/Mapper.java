package ru.merkulyevsasha.excurrency.data.network.mappers;

public interface Mapper<In, Out> {
    Out map(In item);
}
