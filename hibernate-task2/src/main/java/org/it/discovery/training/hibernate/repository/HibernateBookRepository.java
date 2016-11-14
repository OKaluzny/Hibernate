package org.it.discovery.training.hibernate.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.it.discovery.training.hibernate.model.Book;
import org.it.discovery.training.hibernate.util.HibernateUtil;

/**
 * Created by Шарипов on 13.11.2016.
 */
public class HibernateBookRepository implements BookRepository {
	@Override
	public List<Book> findAll() {
		List<Book> books = null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Book.class);
			//criteria.setProjection(Projections.projectionList());
			books = criteria.list();
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
			Criteria criteria = session.createCriteria(Book.class);
			criteria.add(Restrictions.eq("name", name));
			books = criteria.list();
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
            Criteria criteria = session.createCriteria(Book.class);
            criteria.add(Restrictions.ilike("name", text));
            books = criteria.list();
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
            Criteria criteria = session.createCriteria(Book.class);
            criteria.add(Restrictions.gt("pages", pages));
            books = criteria.list();
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
			Criteria criteria = session.createCriteria(Book.class);
			criteria.add(Restrictions.and(Restrictions.eq("pages", pages), Restrictions.eq("name", name)));
			books = criteria.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public int findTotalPages() {
		long sum = 0;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Book.class);
			criteria.setProjection(Projections.sum("pages"));
			sum = (long) criteria.uniqueResult();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return (int)sum;
	}

	@Override
	public List<Book> findSortedBooks() {
		List<Book> books = null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Book.class);
			criteria.addOrder(Order.asc("name"));
			books = criteria.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return books;
	}
}
