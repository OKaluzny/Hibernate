package org.it.discovery.training.hibernate.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.it.discovery.training.hibernate.model.Publisher;
import org.it.discovery.training.hibernate.util.HibernateUtil;

/**
 * Created by Шарипов on 13.11.2016.
 */
public class HibernatePublisherRepository implements PublisherRepository {
    @Override
    public void save(Publisher publisher) {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.save(publisher);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int publisherId) {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Publisher publisher = session.get(Publisher.class, publisherId);
            if (publisher != null) {
                session.delete(publisher);
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Publisher findById(int publisher) {
        Publisher publisher1 = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            publisher1 = session.get(Publisher.class, publisher);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return publisher1;
    }
}
