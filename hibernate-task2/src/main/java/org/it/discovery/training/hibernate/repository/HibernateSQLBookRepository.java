package org.it.discovery.training.hibernate.repository;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.it.discovery.training.hibernate.model.Book;
import org.it.discovery.training.hibernate.util.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Шарипов on 22.11.2016.
 */
public class HibernateSQLBookRepository implements BookRepository {
    @Override
    public List<Book> findAll() {
        List<Book> books = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM Books").addEntity(Book.class);
            books = sqlQuery.list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> findWithName(String name) {
        List<Book> books = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM Books WHERE name =:name").addEntity(Book.class).setParameter("name", name);
            books = sqlQuery.list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> findLikeName(String text) {
        List<Book> books = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM Books WHERE name LIKE :name").addEntity(Book.class).setParameter("name", text);
            books = sqlQuery.list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> findWithMorePages(int pages) {
        List<Book> books = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM Books WHERE pages > :pages").addEntity(Book.class).setParameter("pages", pages);
            books = sqlQuery.list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> searchBooks(String name, int pages) {
        List<Book> books = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM Books WHERE pages=:pages AND name=:name").addEntity(Book.class).setParameter("pages", pages).setParameter("name", name);
            books = sqlQuery.list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public int findTotalPages() {
        long result = 0;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            SQLQuery sqlquery = session.createSQLQuery("select sum(pages) from Book");
            Object obj = sqlquery.getSingleResult();
            result = (long) obj;
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return (int) result;
    }

    @Override
    public List<Book> findSortedBooks() {
        return null;
    }
}
