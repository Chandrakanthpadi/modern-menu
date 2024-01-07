package com.modernmenu.controller;

import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.modernmenu.dto.ItemDTO;
import com.modernmenu.dto.ItemsAddRequestRecord;
import com.modernmenu.service.RestaurantService;
import com.modernmenu.service.impl.ItemServiceImpl;

@SpringBootTest
class ItemControllerTest {


  @Autowired
  ItemServiceImpl itemService;

  @MockBean
  RestaurantService restaurantService;


  @Test
  void testAddItem() {

    List<ItemDTO> itemsToAdd = new ArrayList<>();

    ItemDTO newItem = new ItemDTO("Noodles", 10.34, "Veg");
    itemsToAdd.add(newItem);

    ItemsAddRequestRecord itemAddRequestRecord =
        new ItemsAddRequestRecord("R001", "Snacks", itemsToAdd);

    when(restaurantService.getRestaurantFromDatabaseById(itemAddRequestRecord.restaurantId()))
        .thenReturn(null);

    itemService.addItems(itemAddRequestRecord);

  }

}
