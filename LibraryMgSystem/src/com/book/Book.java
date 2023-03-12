package com.book;

import java.io.Serializable;

public class Book implements Serializable{
	private int isbn;
	private String title;
	private String author;
	private String Publisher;
	private int edition;
	private String subject;
	private int available_quentity;
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return Publisher;
	}
	public void setPublisher(String publisher) {
		Publisher = publisher;
	}
	public int getEdition() {
		return edition;
	}
	public void setEdition(int edition) {
		this.edition = edition;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getAvailable_quentity() {
		return available_quentity;
	}
	public void setAvailable_quentity(int available_quentity) {
		this.available_quentity = available_quentity;
	}
	
	
	public Book(int isbn, String title, String author, String publisher, int edition, String subject,
			int available_quentity) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		Publisher = publisher;
		this.edition = edition;
		this.subject = subject;
		this.available_quentity = available_quentity;
	}
	
	public Book() {
		super();
	}
	
	
	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + ", Publisher=" + Publisher
				+ ", edition=" + edition + ", subject=" + subject + ", available_quentity=" + available_quentity + "]";
	}
	
	
	
}
