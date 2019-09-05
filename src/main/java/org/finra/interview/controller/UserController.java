package org.finra.interview.controller;

import java.security.Principal;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.finra.interview.models.User;
import org.finra.interview.security.UserPrincipal;
import org.finra.interview.security.UserPrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("http://localhost:4200")
public class UserController {

	@Autowired
	private UserPrincipalDetailsService userPrincipalDetailsService;


//	@GetMapping(produces = "application/json")
	@CrossOrigin("http://localhost:4200")
	@RequestMapping("/login")
	public User login(@RequestBody User user) throws Exception{
		UserPrincipal userPrincipal = userPrincipalDetailsService.loadUserByUsername(user.getUsername());

		if(userPrincipal.getPassword().equals(user.getPassword())){
			System.out.println("validateLogin");
			return user;
		}else{
			throw new Exception();
		}

	}

	@GetMapping(produces = "application/json")
	@RequestMapping({ "/currentUser" })
	public String validateLogin() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return authentication.getName();

	}

	@RequestMapping("/user")
	public Principal user(HttpServletRequest request) {
		String authToken = request.getHeader("Authorization").substring("Basic".length()).trim();
		return () -> new String(Base64.getDecoder().decode(authToken)).split(":")[0];
	}
}
