package com.modernmenu.controller;


import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import com.modernmenu.dto.AccountRequest;
import com.modernmenu.entity.Owner;
import com.modernmenu.exception.InvalidLoginException;
import com.modernmenu.exception.ResourceAlreadyExists;
import com.modernmenu.exception.ResourceNotFound;
import com.modernmenu.service.RestaurantService;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class OwnerControllerTest {

  @Autowired
  OwnerController userController;

  @Mock
  private RestaurantService restaurantService;

  @Test
  void testRegistrationFlow() {

    AccountRequest acc =
        new AccountRequest("Muntaj", "Vizag", "9999999999", "admin", "admin", false);
    userController.register(acc);

  }

  @Test
  void testRestaurantAlreadyExists() {

    AccountRequest acc =
        new AccountRequest("Muntaj", "Vizag", "9999999999", "addmin", "admin", false);

    userController.register(acc);

    assertThrows(ResourceAlreadyExists.class, () -> {
      userController.register(acc);
    });

  }


  @Test
  void testUserAlreadyExists() {

    AccountRequest acc =
        new AccountRequest("Muntaj", "Vizag", "9999999999", "addmin", "admin", false);

    userController.register(acc);
    acc.setRestaurantName("Muntaj New");

    assertThrows(ResourceAlreadyExists.class, () -> {
      userController.register(acc);
    });

  }

  @Test
  void testValidLogin() {

    Owner user = new Owner();

    AccountRequest acc =
        new AccountRequest("Muntaj", "Vizag", "9999999999", "admin", "admin", false);

    userController.register(acc);

    user.setUserName("admin");
    user.setPassword("admin");

    userController.login(user);


  }

  @Test
  void testInvalidLogin() {

    Owner user = new Owner();

    AccountRequest acc =
        new AccountRequest("Muntaj", "Vizag", "9999999999", "admin", "admin", false);

    userController.register(acc);

    user.setUserName("admin");
    user.setPassword("adminxxx");

    assertThrows(InvalidLoginException.class, () -> {

      userController.login(user);

    });


  }

  @Test
  void testNoUserlogin() {

    Owner user = new Owner();

    AccountRequest acc =
        new AccountRequest("Muntaj", "Vizag", "9999999999", "admin", "admin", false);

    userController.register(acc);

    user.setUserName("adminyy");
    user.setPassword("adminxxx");

    assertThrows(ResourceNotFound.class, () -> {
      userController.login(user);
    });


  }

}
