package org.it.discovery.training.hibernate.repository;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.it.discovery.training.hibernate.model.Book;
import org.it.discovery.training.hibernate.util.HibernateUtil;

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
        List<Book> books = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Query query = session.createQuery("from Book where name=:name");
            query.setParameter("name", name);
            books = query.getResultList();
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
            Query query = session.createQuery("from Book where name like :name");
            query.setParameter("name", text);
            books = query.getResultList();
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
            Query query = session.createQuery("from Book where pages > :pages");
            query.setParameter("pages", pages);
            books = query.getResultList();
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
            Query query = session.createQuery("from Book where name =:name and pages=:pages");
            query.setParameter("pages", pages);
            query.setParameter("name", name);
            books = query.getResultList();
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
            Query query = session.createQuery("select sum(pages) from Book");
            Object obj = query.getSingleResult();
            result = (long) obj;
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return (int) result;
	}

	@Override
	public List<Book> findSortedBooks() {
        List<Book> books = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Query query = session.createQuery("from Book order by name");
            books = query.getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return books;
	}
}
