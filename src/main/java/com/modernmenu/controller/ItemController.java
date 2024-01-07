package com.modernmenu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.modernmenu.dto.ItemsAddRequestRecord;
import com.modernmenu.entity.Item;
import com.modernmenu.service.impl.ItemServiceImpl;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/item")
@CrossOrigin
@AllArgsConstructor
public class ItemController {

  private final ItemServiceImpl itemService;

  @GetMapping("/get")
  public ResponseEntity<Item> getItem(@RequestParam String itemId) {

    Item item = itemService.getItemFromDatabaseById(itemId);
    return ResponseEntity.status(HttpStatus.OK).body(item);

  }

  @PostMapping("/add")
  public ResponseEntity<HttpStatus> addItems(
      @RequestBody ItemsAddRequestRecord itemAddRequestRecord) {

    itemService.addItems(itemAddRequestRecord);
    return ResponseEntity.ok(HttpStatus.CREATED);

  }

  @PatchMapping("/update")
  public ResponseEntity<HttpStatus> updateItem(@RequestParam String itemId,
      @RequestBody ItemsAddRequestRecord itemRecord) {

    itemService.updateItem(itemId, itemRecord);
    return ResponseEntity.ok(HttpStatus.OK);

  }

  @DeleteMapping("/delete")
  public ResponseEntity<HttpStatus> removeItem(@RequestParam String itemId) {

    itemService.removeItem(itemId);
    return ResponseEntity.ok(HttpStatus.NO_CONTENT);

  }

  @PatchMapping("/notify")
  public ResponseEntity<HttpStatus> updateStatus(@RequestParam String itemId) {

    itemService.updateItemStatus(itemId);
    return ResponseEntity.ok(HttpStatus.OK);

  }

}
