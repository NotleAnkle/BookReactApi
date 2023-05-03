package com.example.BTL.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {
	private UserDAO userDAO = new UserDAO();
	
	@PostMapping("/login")
	public ResponseEntity<User> checkLogin(@RequestBody User user) {
		if(userDAO.checkLogin(user)) {
			// Đăng nhập thành công, trả về mã trạng thái HTTP 200 OK cùng với thông tin user
			return ResponseEntity.ok(user);
		} else {
			// Đăng nhập thất bại, trả về mã trạng thái HTTP 401 Unauthorized
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	@PostMapping("/register")
	public String addUser(@RequestBody User user) {
		return userDAO.addUser(user);
	}
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable int id) {
		return userDAO.selectUser(id);
	}
}
