package com.modernmenu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.modernmenu.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, String> {

  public Optional<Restaurant> findByRestaurantName(String restaurantName);

  @Query(value = "SELECT c.category_name FROM dynamicmenu.category c where c.restaurant_id = ?1;",
      nativeQuery = true)
  public List<String> getCategory(String restaurant_id);

}
