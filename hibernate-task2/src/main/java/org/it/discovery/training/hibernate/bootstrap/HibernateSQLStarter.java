package org.it.discovery.training.hibernate.bootstrap;

import org.hibernate.SessionFactory;
import org.it.discovery.training.hibernate.common.AuditableLogger;
import org.it.discovery.training.hibernate.model.Book;
import org.it.discovery.training.hibernate.model.Person;
import org.it.discovery.training.hibernate.model.Publisher;
import org.it.discovery.training.hibernate.repository.*;
import org.it.discovery.training.hibernate.util.HibernateUtil;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import java.util.List;

/**
 * Created by Шарипов on 22.11.2016.
 */
public class HibernateSQLStarter {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory()) {
            Weld weld = new Weld().disableDiscovery()
                    .packages(HQLPersonRepository.class, AuditableLogger.class)
                    .interceptors(AuditableLogger.class);
            WeldContainer container = weld.initialize();
            HibernateSQLBookRepository sqlBookRepository = container.instance().select(HibernateSQLBookRepository.class).get();
            HibernateSQLPersonRepository sqlPersonRepository = container.instance().select(HibernateSQLPersonRepository.class).get();

            HQLPersonRepository hqlPersonRepository = container.instance().select(HQLPersonRepository.class).get();
            HQLBookRepository hqlBookRepository = container.instance().select(HQLBookRepository.class).get();
            HibernatePersonRepository personRepository = container.instance().select(HibernatePersonRepository.class).get();
            HibernatePublisherRepository publisherRepository = container.instance().select(HibernatePublisherRepository.class).get();

            Publisher publisher = new Publisher();
            publisher.setName("Test publisher");

            Book book1 = new Book();
            book1.setName("Test name third");
            book1.setPages(20);
            Book book2 = new Book();
            book2.setName("Test name second");
            book2.setPages(30);
            Book book3 = new Book();
            book3.setName("Before test name");
            book3.setPages(99);

            publisher.addBook(book1);
            publisher.addBook(book2);
            publisher.addBook(book3);

            Person person2 = new Person();
            person2.setName("Second");
            person2.addBook(book3);
            person2.addBook(book2);
            personRepository.save(person2);
            Person person3 = new Person();
            person3.setName("Third");
            personRepository.save(person3);

            List<Book> books = sqlBookRepository.findAll();
            books.forEach(i -> System.out.println(i.getName() + i.getPages()));

            List<Book> books1 = sqlBookRepository.findWithName("Before test name");
            books1.forEach(i -> System.out.println(i.getName() + i.getPages()));

            List<Book> books2 = sqlBookRepository.findLikeName("T%");
            books2.forEach(i -> System.out.println(i.getName() + i.getPages()));

            List<Book> books3 = sqlBookRepository.findWithMorePages(25);
            books3.forEach(i -> System.out.println(i.getName() + i.getPages()));

            List<Book> books4 = sqlBookRepository.searchBooks("Before test name", 99);
            books4.forEach(i -> System.out.println(i.getName() + i.getPages()));

            //System.out.println(sqlBookRepository.findTotalPages());


            /*books.forEach(i -> System.out.println(i.getName() + i.getPages()));
            List<Book> books5 = hqlBookRepository.findSortedBooks();
            books5.forEach(i -> System.out.println(i.getName() + i.getPages()));

            personRepository.findAll().forEach(i -> System.out.println(i.getName() + " " + i.getBookCount()));

            List<Person> persons = hqlPersonRepository.findPersonWithoutBooks();
            persons.forEach(i -> System.out.println(i.getName() + " " + i.getBookCount()));

            List<Person> persons2 = hqlPersonRepository.findPersonWithBooks(2);
            persons2.forEach(i -> System.out.println(i.getName() + " " + i.getBookCount()));
            System.out.println("PPS!");*/

        }
    }
}
