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
public class Restaurant {

  @Id
  @GenericGenerator(name = "restaurant_id_generator",
      strategy = "com.modernmenu.util.RestaurantIdGenerator")
  @GeneratedValue(generator = "restaurant_id_generator")
  public String restaurantId;

  public String restaurantName;
  public String address;
  public String contactNo;
  public boolean isOpen;

}
