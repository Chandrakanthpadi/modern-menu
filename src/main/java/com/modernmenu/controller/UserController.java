package com.modernmenu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modernmenu.dto.AccountRequest;
import com.modernmenu.entity.Restaurant;
import com.modernmenu.entity.User;
import com.modernmenu.exception.InvalidLoginException;
import com.modernmenu.service.RestaurantService;
import com.modernmenu.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/user")
@CrossOrigin
@AllArgsConstructor
public class UserController {

	private final RestaurantService restaurantService;

	private final UserService userService;

	@PostMapping("/register")
	public ResponseEntity<HttpStatus> addUser(@RequestBody AccountRequest accountRequest) {

		Restaurant newRestaurant = new Restaurant();

		newRestaurant.setRestaurantName(accountRequest.getRestaurantName());
		newRestaurant.setContactNo(accountRequest.getContactNo());
		newRestaurant.setAddress(accountRequest.getAddress());

		String restaurnatId = restaurantService.addRestaurant(newRestaurant).getRestaurantId();

		User newUser = new User();

		newUser.setUserName(accountRequest.getUserName());
		newUser.setPassword(accountRequest.getPassword());
		newUser.setRestaurantId(restaurnatId);

		userService.addUser(newUser);

		return ResponseEntity.ok(HttpStatus.OK);
		
	}

	@PostMapping("/login")
	public Restaurant login(@RequestBody User user) {

		User principalUser = userService.getUser(user.getUserName());

		if (!user.getPassword().equals(principalUser.getPassword())) {
			throw new InvalidLoginException();
		}

		return restaurantService.findByrestaurantId(principalUser.getRestaurantId());
		
	}

}
