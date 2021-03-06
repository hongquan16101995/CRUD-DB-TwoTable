package cg.repository.impl;

import cg.model.Product;
import cg.repository.IProductRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

@Repository
public class ProductRepositoryImpl implements IProductRepository {

    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.conf.xml").buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Product> findAll() {
        String queryStr = "SELECT p FROM Product AS p";
        TypedQuery<Product> query = entityManager.createQuery(queryStr, Product.class);
        return (ArrayList<Product>) query.getResultList();
    }

    @Override
    public Product save(Product product) {
        Transaction transaction = null;
        Product origin;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (product.getId() != 0) {
                origin = findById(product.getId());
                origin.setName(product.getName());
                origin.setPrice(product.getPrice());
                origin.setDescription(product.getDescription());
                origin.setCategory(product.getCategory());
            } else {
                origin = product;
            }
            session.saveOrUpdate(origin);
            transaction.commit();
            return origin;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }

    @Override
    public Product delete(int id) {
        return null;
    }

    @Override
    public Product findById(int id) {
        String queryStr = "SELECT p FROM Product AS p WHERE p.id = :id";
        TypedQuery<Product> query = entityManager.createQuery(queryStr, Product.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
