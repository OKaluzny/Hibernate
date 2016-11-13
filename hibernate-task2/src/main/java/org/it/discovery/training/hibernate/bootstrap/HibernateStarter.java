package org.it.discovery.training.hibernate.bootstrap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.it.discovery.training.hibernate.model.Book;
import org.it.discovery.training.hibernate.model.Publisher;
import org.it.discovery.training.hibernate.repository.HibernatePublisherRepository;
import org.it.discovery.training.hibernate.util.HibernateUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HibernateStarter {

    public static void main(String[] args) {
        try (SessionFactory factory = HibernateUtil.getSessionFactory()) {
            HibernatePublisherRepository repository = new HibernatePublisherRepository();
            Publisher publisher = new Publisher();
            publisher.setName("Test");
            Book book1 = new Book();
            Book book2 = new Book();
            publisher.addBook(book1);
            publisher.addBook(book2);
            repository.save(publisher);
            Publisher publisher1 = repository.findById(1);
            System.out.println(publisher1.getBookCount());
            repository.delete(1);

        }

    }

}
