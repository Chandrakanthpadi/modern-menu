package com.modernmenu.entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Item {

  @Id
  @GenericGenerator(name = "item_id_generator", strategy = "com.modernmenu.util.ItemIdGenerator")
  @GeneratedValue(generator = "item_id_generator")
  public String itemId;

  public String itemName;
  public double price;
  public boolean status;
  public String type;

  public String categoryId;

}
