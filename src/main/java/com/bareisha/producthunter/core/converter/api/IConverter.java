package com.bareisha.producthunter.core.converter.api;

public interface IConverter<S, T> {
    T convert(S source);
}
