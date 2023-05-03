package com.example.BTL.comment;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.BTL.DAO;
import com.example.BTL.book.BookDAO;
import com.example.BTL.user.User;
import com.example.BTL.user.UserDAO;

public class CommentDAO extends DAO {

	public CommentDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<Comment> selectAllComments(int bookcode){
		String sql = "select * from tblComment where idbook = ?";
		List<Comment> comments = new ArrayList<>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, bookcode);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int iduser = resultSet.getInt("iduser");
				int idbook = resultSet.getInt("idbook");
				String content = resultSet.getString("content");
				int rate = resultSet.getInt("rate");
				comments.add(new Comment(id, (new UserDAO()).selectUser(iduser), (new BookDAO()).selectBook(idbook), content, rate  ));
			}

			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return comments;
	}
	
	public boolean insertComment(Comment comment) {
		String sql = "INSERT INTO `library`.`tblComment` (`iduser`, `idbook`, `content`, `rate`) VALUES (?, ?, ?, ?);";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,comment.getUser().getId());
			ps.setInt(2, comment.getBook().getBookcode());
			ps.setString(3, comment.getContent());
			ps.setInt(4, comment.getRate());
			int result = 0;
			result = ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return false;
	}
}
