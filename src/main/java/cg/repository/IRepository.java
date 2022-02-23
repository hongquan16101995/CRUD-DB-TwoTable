package cg.repository;

import java.util.ArrayList;

public interface IRepository<E> {
    ArrayList<E> findAll();

    E save(E e);

    E delete(int id);

    E findById(int id);
}
