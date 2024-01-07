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
public class Category {

  @Id
  @GenericGenerator(name = "category_id_generator",
      strategy = "com.modernmenu.util.CategoryIdGenerator")
  @GeneratedValue(generator = "category_id_generator")
  public String categoryId;

  public String restaurantId;
  public String categoryName;

}
