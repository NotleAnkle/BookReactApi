package com.example.BTL.book;

import java.sql.Date;

public class Book {
	private int bookcode;
	private String title, author;
	private String category;
	private int page;
	private Date releaseDate;
	private String des;
	private String cover;
	public Book() {
	}

	public Book(int bookcode, String title, String author, String category, int page, Date releaseDate, String des,
			String cover) {
		super();
		this.bookcode = bookcode;
		this.title = title;
		this.author = author;
		this.category = category;
		this.page = page;
		this.releaseDate = releaseDate;
		this.des = des;
		this.cover = cover;
	}

	
	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getBookcode() {
		return bookcode;
	}
	public void setBookcode(int bookcode) {
		this.bookcode = bookcode;
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
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date relaseDate) {
		this.releaseDate = relaseDate;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
}
