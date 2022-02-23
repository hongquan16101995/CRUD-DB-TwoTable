package cg.service;

import java.util.ArrayList;

public interface IService<E> {
    ArrayList<E> findAll();

    E save(E e);

    E delete(int id);

    E findById(int id);
}
