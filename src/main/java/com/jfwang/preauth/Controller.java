package com.jfwang.preauth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@RestController
public class Controller {

	   @GetMapping("/")
	   public String main() {
	      return "Main Page, not protected!";
	   }

	   @GetMapping("/both")
	   public String both() {
	      return "Users and Admins both are allowed";
	   }

	   @GetMapping("/user")
	   public String groupOne() {
	      return "user allowed!";
	   }

	   @GetMapping("/admin")
	   public String groupTwo() {
	      return "admin allowed!";
	   }

	   @GetMapping("/Token")
	   public String token() {
		   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		   return auth.toString();
	   }
	}
