package repository;

import javafx.collections.ObservableList;

public interface CrudDao<T> extends SuperDao{
    boolean save(T t);
    ObservableList<T> getAll();
    boolean update(T t);
    boolean delete(String id);
    T search(String id);
}
