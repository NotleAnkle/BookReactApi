package com.example.BTL.comment;

import com.example.BTL.book.Book;
import com.example.BTL.user.User;

public class Comment {
	private int id;
	private User user;
	private Book book;
	private String content;
	private int rate;

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(int id, User user, Book book, String content, int rate) {
		super();
		this.id = id;
		this.user = user;
		this.book = book;
		this.content = content;
		this.rate = rate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}


}
