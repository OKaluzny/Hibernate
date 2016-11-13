package org.it.discovery.training.hibernate.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

/**
 * Book publisher
 * @author morenets
 *
 */
@Entity
@Table(name = "PUBLISHERS")
public class Publisher {
	private int id;
	
	private String name;
	
	private List<Book> books;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

}
