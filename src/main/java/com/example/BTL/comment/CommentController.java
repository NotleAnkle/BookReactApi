package com.example.BTL.comment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CommentController {
	CommentDAO commentDAO = new CommentDAO();
	
	@GetMapping("/comment/{bookcode}")
	public List<Comment> getComments(@PathVariable int bookcode){
		List<Comment> comments = commentDAO.selectAllComments(bookcode);
		return comments;
	}
	@PostMapping("/comment")
	public boolean addComment(@RequestBody Comment comment) {
		return commentDAO.insertComment(comment);
	}
}
