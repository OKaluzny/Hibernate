package org.it.discovery.training.hibernate.repository;

import org.it.discovery.training.hibernate.model.Person;

import java.util.List;

/**
 * Created by Шарипов on 16.11.2016.
 */
public class HQLPersonRepository implements PersonRepository {

    @Override
    public List<Person> findPersonWithoutBooks() {
        return null;
    }

    @Override
    public List<Person> findPersonWithBooks(int number) {
        return null;
    }
}
