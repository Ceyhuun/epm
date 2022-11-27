package com.company.sintra.service;

import java.util.List;

public interface CrudService <T,ID> {
    void create(T t);

    List<T> getAll();

    void update(ID idd, T t);

    void deleteById(ID idd);

    T findById(ID idd);
}
