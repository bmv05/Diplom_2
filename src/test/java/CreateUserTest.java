/*Создание пользователя:
создать уникального пользователя;
создать пользователя, который уже зарегистрирован;
создать пользователя и не заполнить одно из обязательных полей.
 */

import User.User;
import User.UserGenerator;
import User.UserAction;
import User.UserAssertion;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CreateUserTest {
    private String accessToken = "";

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
    }

   @After
    public void deleteUser() {
        if (!accessToken.isEmpty()) {
            ValidatableResponse deleteUser = UserAction.deleteCreatedUser(accessToken);
            UserAssertion.assertUserDelete(deleteUser);
        }
    }

    @Test
    public void successfulUserCreation() {
        User user = UserGenerator.randomUser();
        ValidatableResponse response = UserAction.createNewUser(user);
        UserAssertion.assertSuccessfulCreation(response);
        accessToken = response.extract().path("accessToken");


        //System.out.println("Печать токена: " + accessToken);
        //ValidatableResponse deleteUser = UserAction.deleteCreatedUser(accessToken);
        //UserAssertion.assertUserDelete(deleteUser);
    }

    @Test
    public void errorUserCreationBecauseUserExist() {
        User user = UserGenerator.randomUser();
        ValidatableResponse response = UserAction.createNewUser(user);
        UserAssertion.assertSuccessfulCreation(response);
        ValidatableResponse loginResponse = UserAction.createNewUser(user);
        UserAssertion.assertUserExist(loginResponse);
    }

    @Test
    public void authorizationWithoutEmail() {
        User user = UserGenerator.randomUserWithoutLogin();
        ValidatableResponse response = UserAction.createNewUser(user);
        UserAssertion.assertRequiredFieldsNotFilledIn(response);
    }

    @Test
    public void authorizationWithoutPassword() {
        User user = UserGenerator.randomUserWithoutPassword();
        ValidatableResponse response = UserAction.createNewUser(user);
        UserAssertion.assertRequiredFieldsNotFilledIn(response);
    }

    @Test
    public void authorizationWithoutName() {
        User user = UserGenerator.randomUserWithoutName();
        ValidatableResponse response = UserAction.createNewUser(user);
        UserAssertion.assertRequiredFieldsNotFilledIn(response);
    }
}

