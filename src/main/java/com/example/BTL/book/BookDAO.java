package com.example.BTL.book;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class BookDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/library";
	private String jdbcUsername = "root";
	private String jdbcPassword = "12345678";
	
	private static final String SELECT_ALL_BOOK = "select * from book";
	private static final String SELECT_BOOK_BY_ID = "select * from book where bookcode = ?";
	private static final String INSERT_BOOKS_SQL = "INSERT INTO `library`.`book` (`title`, `author`, `category`, `page`, `releaseDate`, `des`,`cover`) VALUES (?, ?, ?, ?, ?, ?, ?);";
	private static final String UPDATE_BOOKS_SQL = "UPDATE `library`.`book` SET `title` = ?, `author` = ?, `category` = ?, `page` = ?, `releaseDate` = ?, `des` = ?, `cover` = ? WHERE (`bookcode` = ?);";
	private static final String DELETE_BOOKS_SQL = "DELETE FROM `library`.`book` WHERE (`bookcode` = ?);";
	public BookDAO(){
	}
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "12345678");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	public List<Book> selectAllBooks(){
		List<Book> books = new ArrayList<Book>();
		try(Connection connection = getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_BOOK);
			while(resultSet.next()) {
				int bookcode = resultSet.getInt("bookcode");
				String title = resultSet.getString("title");
				String author = resultSet.getString("author");
				String category = resultSet.getString("category");
				int page = resultSet.getInt("page");
				Date releaseDate = resultSet.getDate("releaseDate");
				String des = resultSet.getString("des");
				String cover = resultSet.getString("cover");
				books.add(new Book(bookcode, title, author,category, page, releaseDate, des, cover));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}
	public Book selectBook(int bookcode) {
		Book book = new Book();
		try (Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(SELECT_BOOK_BY_ID);
			ps.setInt(1,bookcode);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				book.setBookcode(result.getInt("bookcode"));
				book.setTitle(result.getString("title"));
				book.setAuthor(result.getString("author"));
				book.setCategory(result.getString("category"));
				book.setReleaseDate(result.getDate("releaseDate"));
				book.setDes(result.getString("des"));
				book.setCover(result.getString("cover"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}
	public String insertBook(Book book) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		int result = 0;
		try {
			connection = getConnection();
			ps = connection.prepareStatement("SELECT * FROM library.book where title = '"+book.getTitle()+"' and author = '"+book.getAuthor()+"';", ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			resultSet = ps.executeQuery();
			
			
			if(resultSet.absolute(1)) return "Already have this book. Try another one!";
			else{
				ps = connection.prepareStatement(INSERT_BOOKS_SQL);
				ps.setString(1, book.getTitle());
				ps.setString(2, book.getAuthor());
				ps.setString(3, book.getCategory());
				ps.setInt(4, book.getPage());
				ps.setDate(5, book.getReleaseDate());
				ps.setString(6, book.getDes());
				ps.setString(7, book.getCover());
				result = ps.executeUpdate();
				ps.close();
				connection.close();
				// Redirect the response to success page
				return "Book saved successfully";
			}
		} // End of try block
		catch (Exception e) {
				e.printStackTrace();
				
		}
		return "Error saving book";
	}
	public String updateBook(Book book) {
		try {
			
			Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(UPDATE_BOOKS_SQL);
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getCategory());
			ps.setInt(4, book.getPage());
			ps.setDate(5, book.getReleaseDate());
			ps.setString(6, book.getDes());
			ps.setString(7, book.getCover());
			ps.setInt(8, book.getBookcode());
			
			
			int result = ps.executeUpdate();

			ps.close();
			connection.close();

			// Redirect the response to success page
			return "Book saved successfully";
		} // End of try block
		catch (Exception e) {
			e.printStackTrace();
		}
		return "Error saving book";
	}
	public String deleteBook(int bookcode) {
		try {
			Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(DELETE_BOOKS_SQL, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ps.setInt(1, bookcode);
			int result = ps.executeUpdate();

			ps.close();
			connection.close();
			return "Book deleted successfully";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Error deleting book"; // tạo trang Error	
	}
}
