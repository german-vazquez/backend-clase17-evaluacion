package dao;

import java.util.List;

public interface IDao <T> {

    T registrar(T t);
    T buscar(Integer id);
    List<T> buscarTodos();

}
