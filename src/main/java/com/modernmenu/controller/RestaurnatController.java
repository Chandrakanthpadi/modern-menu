package com.modernmenu.controller;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.modernmenu.dto.ItemResponseDTO;
import com.modernmenu.entity.Restaurant;
import com.modernmenu.service.RestaurantService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/restaurnat")
@CrossOrigin
@AllArgsConstructor
public class RestaurnatController {

  private final RestaurantService restaurantService;

  @GetMapping("/menu")
  public Map<String, List<ItemResponseDTO>> getMenu(@RequestParam String restaurantId) {
    return restaurantService.getMenu(restaurantId);
  }

  @GetMapping("/get")
  public Restaurant getRestaurant(@RequestParam String restaurantId) {
    return restaurantService.getRestaurantFromDatabaseById(restaurantId);
  }

  @GetMapping("/category")
  public List<String> getCategory(@RequestParam String restaurantId) {
    return restaurantService.getCategory(restaurantId);
  }

  @PatchMapping("/notify")
  public ResponseEntity<HttpStatus> updateStatus(@RequestParam String restaurantId) {
    restaurantService.updateRestaurant(restaurantId);
    return ResponseEntity.ok(HttpStatus.NO_CONTENT);
  }

}
