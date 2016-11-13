package org.it.discovery.training.hibernate.model;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Book publisher
 *
 * @author morenets
 */
@Entity
@Table(name = "PUBLISHERS")
public class Publisher extends BaseEntity {
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

    private List<Book> books;


    @Column(name = "PUBLISHER_NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true,
            mappedBy = "publisher")
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
        book.setPublisher(this);
    }

}
