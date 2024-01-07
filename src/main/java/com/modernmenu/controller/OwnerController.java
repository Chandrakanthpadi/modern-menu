package com.modernmenu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.modernmenu.dto.AccountRequest;
import com.modernmenu.entity.Owner;
import com.modernmenu.entity.Restaurant;
import com.modernmenu.exception.ResourceAlreadyExists;
import com.modernmenu.service.RestaurantService;
import com.modernmenu.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/user")
@CrossOrigin
@AllArgsConstructor
public class OwnerController {

  private final RestaurantService restaurantService;

  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<HttpStatus> register(@RequestBody @Valid AccountRequest accountRequest) {

    if (restaurantService
        .getRestaurantFromDatabaseByRestaurnatName(accountRequest.getRestaurantName())) {
      throw new ResourceAlreadyExists("Restaurant name already exist.");
    }

    if (userService.getUserFromDb(accountRequest.getUserName())) {
      throw new ResourceAlreadyExists("User already exist.");
    }

    String restaurantId = restaurantService.addRestaurant(accountRequest);
    userService.addUser(accountRequest, restaurantId);

    return ResponseEntity.ok(HttpStatus.CREATED);

  }

  @PostMapping("/login")
  public Restaurant login(@RequestBody Owner user) {

    String restaurantId = userService.perfomLogin(user);
    return restaurantService.getRestaurantFromDatabaseById(restaurantId);

  }

}
