package com.modernmenu.service.impl;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.modernmenu.dto.ItemDTO;
import com.modernmenu.dto.ItemResponseDTO;
import com.modernmenu.dto.ItemsAddRequestRecord;
import com.modernmenu.entity.Category;
import com.modernmenu.entity.Item;
import com.modernmenu.exception.ResourceNotFound;
import com.modernmenu.repository.CategoryRepository;
import com.modernmenu.repository.ItemRepository;
import com.modernmenu.service.ItemService;
import com.modernmenu.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

  private final ItemRepository itemRepository;
  private final CategoryRepository categoryRepository;
  private final RestaurantService restaurantService;

  @Override
  public Item getItemFromDatabaseById(String itemId) {

    Optional<Item> itemFromDatabase = itemRepository.findById(itemId);
    if (!itemFromDatabase.isPresent()) {
      throw new ResourceNotFound("Item not found.");
    }
    return itemFromDatabase.get();

  }


  @Override
  public void addItems(ItemsAddRequestRecord itemsAddRequestRecord) {

    // To check if the restaurant is present if not throw restaurant not found exception.
    restaurantService.getRestaurantFromDatabaseById(itemsAddRequestRecord.restaurantId());

    String categoryId = checkForCategoryAndGetCategoryId(itemsAddRequestRecord.restaurantId(),
        itemsAddRequestRecord.categoryName());

    log.info(itemsAddRequestRecord.toString());

    for (ItemDTO item : itemsAddRequestRecord.items()) {
      addItem(itemsAddRequestRecord.restaurantId(), categoryId, item);
    }

  }

  public void addItem(String restaurantId, String categoryId, @Valid ItemDTO itemRecord) {

    log.debug("addItem Request {}", itemRecord);

    Optional<ItemResponseDTO> isItemExitsInDB =
        itemRepository.getItem(itemRecord.itemName(), restaurantId);

    if (isItemExitsInDB.isPresent()) {
      return;
    }

    Item newItem = new Item();
    newItem.setItemName(itemRecord.itemName());
    newItem.setCategoryId(categoryId);
    newItem.setPrice(itemRecord.price());
    newItem.setStatus(false);
    newItem.setType(itemRecord.type());
    newItem.setStatus(true);

    itemRepository.saveAndFlush(newItem);
    log.debug("Item has been added {}", newItem);

  }

  @Override
  public void updateItem(String itemId, ItemsAddRequestRecord itemsAddRequestRecord) {

    log.debug("updateItem Request : {}", itemsAddRequestRecord);

    restaurantService.getRestaurantFromDatabaseById(itemsAddRequestRecord.restaurantId());

    Item item = getItemFromDatabaseById(itemId);
    ItemDTO requestItem = itemsAddRequestRecord.items().get(0);

    item.setItemName(requestItem.itemName());
    item.setPrice(requestItem.price());
    item.setType(requestItem.type());

    String categoryId = checkForCategoryAndGetCategoryId(itemsAddRequestRecord.restaurantId(),
        itemsAddRequestRecord.categoryName());
    item.setCategoryId(categoryId);

    itemRepository.saveAndFlush(item);

    log.debug("Item updated {}", item);

  }

  @Override
  public void removeItem(String itemId) {

    Item item = getItemFromDatabaseById(itemId);

    itemRepository.deleteById(itemId);
    log.debug("Item has been deleted {}", item);

  }

  @Override
  public void updateItemStatus(String itemId) {

    Item item = getItemFromDatabaseById(itemId);
    item.setStatus(!item.isStatus());

    itemRepository.saveAndFlush(item);
    log.debug("Item status has been updated {}", item);

  }

  public String checkForCategoryAndGetCategoryId(String restaurantId, String categoryName) {

    Optional<Category> isCategoryExitsInDB =
        categoryRepository.findByRestaurantIdAndCategoryName(restaurantId, categoryName);

    if (isCategoryExitsInDB.isPresent()) {
      return isCategoryExitsInDB.get().getCategoryId();
    }

    Category newCategory = new Category();

    newCategory.setRestaurantId(restaurantId);
    newCategory.setCategoryName(categoryName);
    newCategory = categoryRepository.saveAndFlush(newCategory);

    log.debug("New Category has been added {}", newCategory);
    return newCategory.getCategoryId();

  }


}
