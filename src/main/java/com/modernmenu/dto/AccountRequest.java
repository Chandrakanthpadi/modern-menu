package com.modernmenu.dto;

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

	public String restaurantName;
	public String address;
	public String contactNo;
	public String userName;
	public String password;
	public boolean isOpen;
	
}
