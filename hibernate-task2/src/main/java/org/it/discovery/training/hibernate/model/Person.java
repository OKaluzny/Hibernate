package org.it.discovery.training.hibernate.model;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Person who can write books, for example
 *
 * @author admin
 */
@Entity
@Table(name = "PERSONS")
public class Person extends BaseEntity {
    private int bookCount;

    @Formula("(select count(books.id) FROM BOOKS books " +
            "where books.publisher_id = id)")
    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    private String name;

    /**
     * Books that person has written
     */
    private List<Book> books;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            orphanRemoval = true, mappedBy = "author")
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        if (books == null) {
            books = new ArrayList<>();
        }
        books.add(book);
        book.setAuthor(this);
    }
}
