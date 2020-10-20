package ru.vsu.cs.newsstand.core.dao;

import java.util.List;

public interface IDataService<T> {

    T add(T t);

    T get(Long id);

    List<T> getAll();

    boolean delete(Long id);

    T update(T t);

}
