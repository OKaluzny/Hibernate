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
            Publisher publisher2 = new Publisher();
            publisher.setName("Test");
            publisher2.setName("Test2");
            List<Book> books = Arrays.asList(new Book(), new Book());
            publisher.setBooks(books);
            publisher2.setBooks(books);
            repository.save(publisher);
            repository.save(publisher2);
            repository.findById(1);
            repository.delete(1);

        }

    }

}
