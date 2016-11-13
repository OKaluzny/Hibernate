package org.it.discovery.training.hibernate.bootstrap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.it.discovery.training.hibernate.util.HibernateUtil;

public class HibernateStarter {

	public static void main(String[] args) {
		Session session = null;
		try (SessionFactory factory = HibernateUtil.getSessionFactory()) {

		} catch (Exception ex) {
			if (session != null) {
				session.getTransaction().rollback();
			}
		}

	}

}
