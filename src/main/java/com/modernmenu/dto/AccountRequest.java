package com.modernmenu.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AccountRequest {

  @NotBlank(message = "Restaurant name cannot be blank.")
  public String restaurantName;
  @NotBlank(message = "Address cannot be blank.")
  public String address;
  @NotBlank(message = "Contact number cannot be blank.")
  public String contactNo;
  @NotBlank(message = "Username cannot be blank.")
  public String userName;
  @NotBlank(message = "Password cannot be blank")
  public String password;
  public boolean isOpen = true;

}
