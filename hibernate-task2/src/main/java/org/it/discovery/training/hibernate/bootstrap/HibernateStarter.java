package org.it.discovery.training.hibernate.bootstrap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.it.discovery.training.hibernate.model.Book;
import org.it.discovery.training.hibernate.model.Publisher;
import org.it.discovery.training.hibernate.repository.HQLBookRepository;
import org.it.discovery.training.hibernate.repository.HibernateBookRepository;
import org.it.discovery.training.hibernate.repository.HibernatePublisherRepository;
import org.it.discovery.training.hibernate.util.HibernateUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HibernateStarter {

    public static void main(String[] args) {
        try (SessionFactory factory = HibernateUtil.getSessionFactory()) {
            HibernatePublisherRepository repository = new HibernatePublisherRepository();
            HibernateBookRepository bookRepository = new HibernateBookRepository();
            HQLBookRepository hqlBookRepository = new HQLBookRepository();
            Publisher publisher = new Publisher();
            publisher.setName("Test");
            Book book1 = new Book();
            book1.setName("Test name first");
            Book book2 = new Book();
            book2.setName("Test name second");
            publisher.addBook(book1);
            publisher.addBook(book2);
            repository.save(publisher);
            Publisher publisher1 = repository.findById(1);
            System.out.println(publisher1.getBookCount());
            //repository.delete(1);
            List<Book> testBookList = bookRepository.findWithName("Test name first");
            System.out.println(testBookList.size());
            System.out.println(testBookList);
            List<Book> testHQLBookList = hqlBookRepository.findAll();
            System.out.println(testHQLBookList.size());
            System.out.println(testHQLBookList);

        }

    }

}
