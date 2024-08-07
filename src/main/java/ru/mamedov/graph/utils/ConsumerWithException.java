package ru.mamedov.graph.utils;

@FunctionalInterface
public interface ConsumerWithException<T> {
    void accept(T t) throws Exception;
}