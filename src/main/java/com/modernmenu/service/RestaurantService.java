package com.modernmenu.service;

import java.util.List;
import java.util.Map;
import com.modernmenu.dto.AccountRequest;
import com.modernmenu.dto.ItemResponseDTO;
import com.modernmenu.entity.Restaurant;
import jakarta.validation.Valid;

public interface RestaurantService {

  String addRestaurant(@Valid AccountRequest accountRequest);

  void removeRestaurant(Restaurant restaurant);

  void updateRestaurant(String restaurantId);

  Restaurant getRestaurantFromDatabaseById(String restaurantId);

  List<String> getCategory(String restaurantId);

  Map<String, List<ItemResponseDTO>> getMenu(String restaurantId);

  boolean getRestaurantFromDatabaseByRestaurnatName(String restaurantName);

}
