package org.it.discovery.training.hibernate.model;

import java.util.List;

/**
 * Book publisher
 * @author morenets
 *
 */
public class Publisher {
	private int id;
	
	private String name;
	
	private List<Book> books;

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

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

}
