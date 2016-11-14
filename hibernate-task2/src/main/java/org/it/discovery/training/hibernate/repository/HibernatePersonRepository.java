package org.it.discovery.training.hibernate.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.it.discovery.training.hibernate.model.Person;
import org.it.discovery.training.hibernate.util.HibernateUtil;

/**
 * Created by Шарипов on 13.11.2016.
 */
public class HibernatePersonRepository implements PersonRepository {
	@Override
	public List<Person> findPersonWithoutBooks() {
		return null;
	}

	@Override
	public List<Person> findPersonWithBooks(int number) {
		return null;
	}
}
