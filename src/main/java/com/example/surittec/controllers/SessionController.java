package com.example.surittec.controllers;

import com.example.surittec.models.Address;
import com.example.surittec.models.enums.TypeUser;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.surittec.models.User;
import com.example.surittec.models.dtos.LoginDTO;
import com.example.surittec.services.UserService;

import java.util.List;

import static com.example.surittec.models.enums.TypeUser.ADMIN;
import static com.example.surittec.models.enums.TypeUser.CLIENT;

@RestController
@RequestMapping(path = "/auth")
@CrossOrigin(origins = "*")
public class SessionController {
	
	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<?> show(@RequestBody LoginDTO auth) {
		User user = userService.searchUser(auth.getLogin(), auth.getPassword());

		if (user == null) {
			return ResponseEntity.badRequest().body("Erro ao se autenticar no sistema");
		}
		user.setPassword(null);
		return ResponseEntity.ok().body(user);
	}
}
