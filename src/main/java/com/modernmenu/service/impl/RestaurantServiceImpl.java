package com.modernmenu.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.modernmenu.dto.AccountRequest;
import com.modernmenu.dto.ItemResponseDTO;
import com.modernmenu.entity.Restaurant;
import com.modernmenu.exception.ResourceNotFound;
import com.modernmenu.repository.CategoryRepository;
import com.modernmenu.repository.ItemRepository;
import com.modernmenu.repository.RestaurantRepository;
import com.modernmenu.service.RestaurantService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {


  private final RestaurantRepository restaurantRepository;
  private final CategoryRepository categoryRepository;
  private final ItemRepository itemRepository;

  @Override
  public Restaurant getRestaurantFromDatabaseById(String restaurantId) {

    Optional<Restaurant> restaurantFromDatabase = restaurantRepository.findById(restaurantId);
    if (!restaurantFromDatabase.isPresent()) {
      throw new ResourceNotFound("Restaurnat does not exists.");
    }
    return restaurantFromDatabase.get();

  }

  @Override
  public String addRestaurant(AccountRequest accountRequest) {

    Restaurant newRestaurant = new Restaurant();

    newRestaurant.setRestaurantName(accountRequest.getRestaurantName());
    newRestaurant.setContactNo(accountRequest.getContactNo());
    newRestaurant.setAddress(accountRequest.getAddress());
    newRestaurant.setOpen(accountRequest.isOpen());

    return restaurantRepository.saveAndFlush(newRestaurant).getRestaurantId();

  }

  @Override
  public void updateRestaurant(String restaurantId) {

    Restaurant restaurant = getRestaurantFromDatabaseById(restaurantId);
    restaurant.setOpen(!restaurant.isOpen());
    restaurantRepository.saveAndFlush(restaurant);

  }

  @Override
  public List<String> getCategory(String restaurantId) {
    return categoryRepository.findByRestaurantId(restaurantId).stream().map(cateogry -> {
      return cateogry.getCategoryName();
    }).collect(Collectors.toList());
  }


  @Override
  public Map<String, List<ItemResponseDTO>> getMenu(String restaurantId) {

    // Check if restaurant is present or not.
    getRestaurantFromDatabaseById(restaurantId);

    List<ItemResponseDTO> menuList = itemRepository.getMenu(restaurantId);
    Map<String, List<ItemResponseDTO>> menuMap =
        menuList.stream().sorted(Comparator.comparing(ItemResponseDTO::getPrice))
            .sorted(Comparator.comparing(ItemResponseDTO::getStatus, Collections.reverseOrder()))
            .collect(Collectors.groupingBy(ItemResponseDTO::getCategoryName));

    return menuMap;

  }


  @Override
  public void removeRestaurant(Restaurant restaurant) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean getRestaurantFromDatabaseByRestaurnatName(String restaurantName) {

    Optional<Restaurant> isRestaurnatExits =
        restaurantRepository.findByRestaurantName(restaurantName);

    if (isRestaurnatExits.isPresent()) {
      return true;
    }

    return false;

  }

}
