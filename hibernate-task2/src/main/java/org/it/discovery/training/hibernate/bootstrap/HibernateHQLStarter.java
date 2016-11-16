package org.it.discovery.training.hibernate.bootstrap;

import org.hibernate.SessionFactory;
import org.it.discovery.training.hibernate.model.Book;
import org.it.discovery.training.hibernate.model.Person;
import org.it.discovery.training.hibernate.model.Publisher;
import org.it.discovery.training.hibernate.repository.HQLBookRepository;
import org.it.discovery.training.hibernate.repository.HibernatePersonRepository;
import org.it.discovery.training.hibernate.repository.HibernatePublisherRepository;
import org.it.discovery.training.hibernate.util.HibernateUtil;

import java.util.List;

/**
 * Created by Шарипов on 16.11.2016.
 */
public class HibernateHQLStarter {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory()) {
            HQLBookRepository hqlBookRepository = new HQLBookRepository();
            HibernatePublisherRepository publisherRepository = new HibernatePublisherRepository();
            HibernatePersonRepository personRepository = new HibernatePersonRepository();

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
            personRepository.save(person2);
            Person person3 = new Person();
            person3.setName("Third");
            personRepository.save(person3);

            List<Book> books = hqlBookRepository.findAll();
            books.forEach(i -> System.out.println(i.getName() + i.getPages()));

            List<Book> books1 = hqlBookRepository.findWithName("Before test name");
            books1.forEach(i -> System.out.println(i.getName() + i.getPages()));

            List<Book> books2 = hqlBookRepository.findLikeName("B%");
            books2.forEach(i -> System.out.println(i.getName() + i.getPages()));

            List<Book> books3 = hqlBookRepository.findWithMorePages(25);
            books3.forEach(i -> System.out.println(i.getName() + i.getPages()));

            List<Book> books4 = hqlBookRepository.searchBooks("Before test name", 199);
            books4.forEach(i -> System.out.println(i.getName() + i.getPages()));

            System.out.println(hqlBookRepository.findTotalPages());


            books.forEach(i -> System.out.println(i.getName() + i.getPages()));
            List<Book> books5 = hqlBookRepository.findSortedBooks();
            books5.forEach(i -> System.out.println(i.getName() + i.getPages()));


        }
    }
}
