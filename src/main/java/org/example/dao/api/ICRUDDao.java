package org.example.dao.api;

import java.sql.SQLException;
import java.util.List;

public interface ICRUDDao <T> {
    List<T> get();

    T get(long id);

    T save(T item);
}
