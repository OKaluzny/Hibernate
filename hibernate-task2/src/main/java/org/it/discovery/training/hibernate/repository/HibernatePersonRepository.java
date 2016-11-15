package org.it.discovery.training.hibernate.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.it.discovery.training.hibernate.model.Person;
import org.it.discovery.training.hibernate.util.HibernateUtil;

/**
 * Created by Шарипов on 13.11.2016.
 */
public class HibernatePersonRepository implements PersonRepository {

	public void save(Person person) {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.save(person);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	public List<Person> findAll() {
		List<Person> persons = null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Person.class);
			persons = criteria.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return persons;
	}


	@Override
	public List<Person> findPersonWithoutBooks() {
		List<Person> persons;
		List<Person> allPerson  = null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Person.class);
			allPerson = criteria.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		persons = allPerson.stream().filter(i -> i.getBookCount() == 0).collect(Collectors.toList());
		return persons;
	}

	@Override
	public List<Person> findPersonWithBooks(int number) {
		List<Person> persons;
		List<Person> allPerson  = null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Person.class);
			allPerson = criteria.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		persons = allPerson.stream().filter(i -> i.getBookCount() == number).collect(Collectors.toList());
		return persons;
	}
}
