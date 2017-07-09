package org.it.discovery.training.hibernate.bootstrap;

import org.hibernate.SessionFactory;
import org.it.discovery.training.hibernate.model.Book;
import org.it.discovery.training.hibernate.model.Person;
import org.it.discovery.training.hibernate.model.Publisher;
import org.it.discovery.training.hibernate.repository.HQLBookRepository;
import org.it.discovery.training.hibernate.repository.HibernateBookRepository;
import org.it.discovery.training.hibernate.repository.HibernatePersonRepository;
import org.it.discovery.training.hibernate.repository.HibernatePublisherRepository;
import org.it.discovery.training.hibernate.util.HibernateUtil;

import java.util.List;

public class HibernateStarter {

    public static void main(String[] args) {

        try (SessionFactory factory = HibernateUtil.getSessionFactory()) {
            HibernatePublisherRepository repository = new HibernatePublisherRepository();
            HibernateBookRepository bookRepository = new HibernateBookRepository();
            HQLBookRepository hqlBookRepository = new HQLBookRepository();
            HibernatePersonRepository personRepository = new HibernatePersonRepository();

            Publisher publisher = new Publisher();
            publisher.setName("Test");

            Book book1 = new Book();
            book1.setName("Test name first");
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
            repository.save(publisher);

            Person person1 = new Person();
            person1.setName("First");
            person1.addBook(book1);
            person1.addBook(book2);
            personRepository.save(person1);

            Person person2 = new Person();
            person2.setName("Second");
            person2.addBook(book3);
            personRepository.save(person2);
            Person person3 = new Person();
            person3.setName("Third");
            personRepository.save(person3);


            Publisher publisher1 = repository.findById(1);
            System.out.println(publisher1.getBookCount());
            // repository.delete(1);

            List<Book> testBookList = bookRepository.findWithName("Test name first");
            System.out.println(testBookList.size());
            System.out.println(testBookList);

            List<Book> testHQLBookList = hqlBookRepository.findAll();
            System.out.println(testHQLBookList.size());
            System.out.println(testHQLBookList);

            List<Book> testFindLikeBook = bookRepository.findLikeName("Test%");
            testFindLikeBook.forEach(System.out::println);

            List<Book> testFindWithMorePagesBook = bookRepository.findWithMorePages(19);
            testFindWithMorePagesBook.forEach(i -> System.out.println(i.getName() + " " + i.getPages()));

            List<Book> testSearchBookWithGivenNameAndPages = bookRepository.searchBooks("Test name first", 20);
            System.out.println(testSearchBookWithGivenNameAndPages.size());
            testSearchBookWithGivenNameAndPages.forEach(i -> System.out.println(i.getName() + " " + i.getPages()));

            System.out.println(bookRepository.findTotalPages());


            List<Book> findAllBooks = bookRepository.findAll();
            findAllBooks.forEach(i -> System.out.println(i.getName() + " " + i.getPages()));

            List<Book> testFindSortedBooks = bookRepository.findSortedBooks();
            testFindSortedBooks.forEach(i -> System.out.println(i.getName() + " " + i.getPages()));


            //findAll persons
            personRepository.findAll().forEach(i -> System.out.println(i.getName() + " " + i.getBookCount()));

            //find persons without book
            List<Person> persons = personRepository.findPersonWithoutBooks();
            System.out.println(persons);
            persons.forEach(i -> System.out.println(i.getName() + " " + i.getBookCount()));


            //find persons with book
            List<Person> personsWithBooks = personRepository.findPersonWithBooks(1);
            System.out.println(persons);
            personsWithBooks.forEach(i -> System.out.println(i.getName() + " " + i.getBookCount()));
        }
    }
}
