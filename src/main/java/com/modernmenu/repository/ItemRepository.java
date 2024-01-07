package com.modernmenu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.modernmenu.dto.ItemResponseDTO;
import com.modernmenu.entity.Item;

public interface ItemRepository extends JpaRepository<Item, String> {


  @Query(
      value = "SELECT i.item_id as itemId, i.item_name as itemName, i.price, i.status,i.type,c.category_name as categoryName FROM dynamicmenu.item i "
          + "JOIN dynamicmenu.category c on i.category_id = c.category_id "
          + "JOIN dynamicmenu.restaurant r on c.restaurant_id = r.restaurant_id "
          + "WHERE r.restaurant_id = ?1",
      nativeQuery = true)
  public List<ItemResponseDTO> getMenu(String restaurnatName);

  @Query(
      value = "SELECT i.item_id as itemId, i.item_name as itemName, i.price, i.status,i.type,c.category_name as categoryName FROM dynamicmenu.item i\n"
          + "JOIN dynamicmenu.category c\n" + "ON i.category_id = c.category_id\n"
          + "WHERE i.item_name = ?1 AND c.restaurant_id = ?2",
      nativeQuery = true)
  public Optional<ItemResponseDTO> getItem(String itemName, String restaurantId);

}
