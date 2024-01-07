package com.modernmenu;


import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.modernmenu.controller.OwnerController;
import com.modernmenu.dto.AccountRequest;
import com.modernmenu.service.RestaurantService;

@SpringBootTest
class ModernMenuApplicationTests {

  @MockBean
  OwnerController userController;

  @MockBean
  private RestaurantService restaurantService;

  @Test
  void testUserRegistration() {

    AccountRequest acc =
        new AccountRequest("Muntaj", "Vizag", "9999999999", "admin", "admin", false);
    userController.register(acc);

  }

  @Test
  void testUserAlreadyRegistered() {

    AccountRequest acc =
        new AccountRequest("Muntaj", "Vizag", "9999999999", "admin", "admin", false);

    when(restaurantService.getRestaurantFromDatabaseByRestaurnatName(acc.getRestaurantName()))
        .thenReturn(true);

    //// ResourceAlreadyExists exception = assertThrows(ResourceAlreadyExists.class, () -> {
    //// userController.addUser(acc);
    // // throw new ResourceAlreadyExists("Null");
    // });

  }


}
