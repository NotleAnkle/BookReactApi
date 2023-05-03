package com.example.BTL.book;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
@CrossOrigin
public class BookController {
	private BookDAO bookDAO = new BookDAO(); 
	
		@GetMapping("books")
		public List<Book> getBooks(Model model) throws IOException {
			List<Book> books =  bookDAO.selectAllBooks();
			model.addAttribute("books",books);
			return books;
		}
		
		@GetMapping("/book/{bookcode}")
		public Book getItem(Model model, @PathVariable String bookcode) throws NumberFormatException {
			model.addAttribute("bookcode", bookcode);
			Book book = bookDAO.selectBook(Integer.valueOf(bookcode));
			return book;
		}
		
		@PostMapping("book/save/{bookcode}")
		public String addItem(@RequestBody Book book, @PathVariable String bookcode) {
			return bookDAO.insertBook(book);
		}
		
		@PutMapping("book/save/{bookcode}")
		public String updateItem(@RequestBody Book book, @PathVariable String bookcode) {
			return bookDAO.updateBook(book);
		}
		
		@DeleteMapping("book/{bookcode}")
		public String deleteItem(@PathVariable String bookcode) {
			return bookDAO.deleteBook(Integer.valueOf(bookcode));
		}
}
