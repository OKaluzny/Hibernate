package org.it.discovery.training.hibernate.repository;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.it.discovery.training.hibernate.model.Book;
import org.it.discovery.training.hibernate.util.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Шарипов on 13.11.2016.
 */
public class HQLBookRepository implements BookRepository {
    @Override
    public List<Book> findAll() {
        List<Book> books = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Query query = session.createQuery("from Book");
            books = query.getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return books;

    }

    @Override
    public List<Book> findWithName(String name) {


        return null;
    }

    @Override
    public List<Book> findLikeName(String text) {
        return null;
    }

    @Override
    public List<Book> findWithMorePages(int pages) {
        return null;
    }

    @Override
    public List<Book> searchBooks(String name, int pages) {
        return null;
    }

    @Override
    public int findTotalPages() {
        return 0;
    }

    @Override
    public List<Book> findSortedBooks() {
        return null;
    }
}
