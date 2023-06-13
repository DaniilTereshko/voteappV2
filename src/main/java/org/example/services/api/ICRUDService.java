package org.example.services.api;

import java.util.List;

public interface ICRUDService<T, S> {
    List<T> get();
    T get(long id);
    T save(S item);
}
