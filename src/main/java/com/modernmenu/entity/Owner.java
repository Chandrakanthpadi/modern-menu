package com.modernmenu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class Owner {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int userId;

  public String userName;
  public String password;
  public String restaurantId;

}
