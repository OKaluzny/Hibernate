package org.it.discovery.training.hibernate.repository;

import com.jcabi.aspects.Loggable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.it.discovery.training.hibernate.model.Person;
import org.it.discovery.training.hibernate.util.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Шарипов on 16.11.2016.
 */
public class HQLPersonRepository implements PersonRepository {

    @Override
    @Loggable
    public List<Person> findPersonWithoutBooks() {
        List<Person> persons = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Query query = session.createQuery("from Person person where "
                + "size(person.books) = 0");
            persons = query.getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return persons;

    }

    @Loggable
    @Override
    public List<Person> findPersonWithBooks(int number) {
        List<Person> persons = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Query query = session.createQuery("from Person person where "
                    + "size(person.books) =:number");
            query.setParameter("number", number);
            persons = query.getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return persons;
    }
}
