package org.it.discovery.training.hibernate.repository;

import org.it.discovery.training.hibernate.model.Book;

import java.util.List;

/**
 * Created by Шарипов on 13.11.2016.
 */
public class HibernateBookRepository implements BookRepository {
    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public List<Book> findWithName(String name) {
        return null;
    }

    @Override
    public List<Book> findLikeName(String text) {
        return null;
    }

    @Override
    public List<Book> findWithMorePages(int pages) {
        return null;
    }

    @Override
    public List<Book> searchBooks(String name, int pages) {
        return null;
    }

    @Override
    public int findTotalPages() {
        return 0;
    }

    @Override
    public List<Book> findSortedBooks() {
        return null;
    }
}
