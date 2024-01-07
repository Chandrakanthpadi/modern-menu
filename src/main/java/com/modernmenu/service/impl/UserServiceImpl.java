package com.modernmenu.service.impl;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.modernmenu.dto.AccountRequest;
import com.modernmenu.entity.Owner;
import com.modernmenu.exception.InvalidLoginException;
import com.modernmenu.exception.ResourceNotFound;
import com.modernmenu.repository.UserRepository;
import com.modernmenu.service.UserService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {


  private final UserRepository userRepository;

  @Override
  public void addUser(AccountRequest accountRequest, String restaurantId) {

    Owner newUser = new Owner();

    newUser.setUserName(accountRequest.getUserName());
    newUser.setPassword(accountRequest.getPassword());
    newUser.setRestaurantId(restaurantId);

    userRepository.saveAndFlush(newUser);

  }

  @Override
  public Optional<Owner> getUser(String userName) {
    return userRepository.findByUserName(userName);
  }

  @Override
  public boolean getUserFromDb(String username) {

    Optional<Owner> isUserExits = getUser(username);

    if (isUserExits.isPresent()) {
      return true;
    }

    return false;

  }

  @Override
  public String perfomLogin(Owner user) {

    Optional<Owner> principalUser = getUser(user.getUserName());

    if (!principalUser.isPresent()) {
      throw new ResourceNotFound("User doesn't Exists.");
    }

    Owner pUser = principalUser.get();

    if (!user.getPassword().equals(pUser.getPassword())) {
      throw new InvalidLoginException();
    }

    return pUser.getRestaurantId();

  }

}
