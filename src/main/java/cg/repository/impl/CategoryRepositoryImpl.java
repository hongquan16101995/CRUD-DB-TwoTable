package cg.repository.impl;

import cg.model.Category;
import cg.repository.ICategoryRepository;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

@Repository
public class CategoryRepositoryImpl implements ICategoryRepository {

    private static EntityManager entityManager;

    static {
        try {
            SessionFactory sessionFactory = new Configuration().configure("hibernate.conf.xml").buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Category> findAll() {
        String queryStr = "SELECT c FROM Category AS c";
        TypedQuery<Category> query = entityManager.createQuery(queryStr, Category.class);
        return (ArrayList<Category>) query.getResultList();
    }

    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public Category delete(int id) {
        return null;
    }

    @Override
    public Category findById(int id) {
        String queryStr = "SELECT c FROM Category AS c WHERE c.c_id = :id";
        TypedQuery<Category> query = entityManager.createQuery(queryStr, Category.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
