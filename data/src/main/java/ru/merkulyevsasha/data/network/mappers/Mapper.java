package ru.merkulyevsasha.data.network.mappers;

public interface Mapper<In, Out> {
    Out map(In item);
}
