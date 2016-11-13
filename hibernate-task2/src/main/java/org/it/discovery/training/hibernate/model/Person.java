package org.it.discovery.training.hibernate.model;

import javax.persistence.*;
import java.util.List;

/**
 * Person who can write books, for example
 * @author admin
 *
 */
@Entity
@Table(name = "PERSONS")
public class Person {
	private int id;
	
	private String name;
	
	/**
	 * Books that person has written
	 */
	private List<Book> books;

	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
}
