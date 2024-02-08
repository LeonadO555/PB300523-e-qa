package integration;

import integration.user.UserApi;
import org.testng.annotations.Test;

public class UserApiTest {

    UserApi userApi;

    @Test
    public void userCanCreateNewUserApiTest() {
        String email = "alexpah779@email.com";
        String password = "alexpah779@email.com";

        userApi = new UserApi();
        String token = userApi.registrationNewUser(email, password, 201);

        userApi.getNewUserActivation(200, token);
        userApi.login(email, password,200);
    }

}
