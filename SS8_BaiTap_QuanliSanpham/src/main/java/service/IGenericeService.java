package service;

import java.util.List;

public interface IGenericeService<E> {
    List<E> findAll();
    void save (E e);
    E findById(int id);
    void remove(int id);
}
