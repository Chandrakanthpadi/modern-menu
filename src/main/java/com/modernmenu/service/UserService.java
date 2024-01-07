package com.modernmenu.service;

import java.util.Optional;
import com.modernmenu.dto.AccountRequest;
import com.modernmenu.entity.Owner;
import jakarta.validation.Valid;

public interface UserService {

  public Optional<Owner> getUser(String string);

  boolean getUserFromDb(String username);

  void addUser(@Valid AccountRequest accountRequest, String restaurantId);

  String perfomLogin(Owner user);

}
