package com.modernmenu.service;

import com.modernmenu.dto.ItemsAddRequestRecord;
import com.modernmenu.entity.Item;

public interface ItemService {

  Item getItemFromDatabaseById(String itemId);

  void addItems(ItemsAddRequestRecord itemsAddRequestRecord);

  void updateItemStatus(String itemId);

  void removeItem(String itemId);

  void updateItem(String itemId, ItemsAddRequestRecord itemRecord);

}
