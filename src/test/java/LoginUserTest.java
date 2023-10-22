/*
Логин пользователя:
        логин под существующим пользователем,
        логин с неверным логином и паролем.
*/

import User.User;
import User.UserGenerator;
import User.UserAction;
import User.UserAssertion;
import User.UserCredentials;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginUserTest {
    private String accessToken = "";
    User user = UserGenerator.randomUser();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        ValidatableResponse response = UserAction.createNewUser(user);
        UserAssertion.assertSuccessfulCreation(response);
        accessToken = response.extract().path("accessToken");
    }
    @After
    public void deleteUser() {
        if (!accessToken.isEmpty()) {
            ValidatableResponse deleteUser = UserAction.deleteCreatedUser(accessToken);
            UserAssertion.assertUserDelete(deleteUser);
        }
    }
    @Test
    public void successfulAuthorizationUser() {
        UserCredentials userCredential = UserCredentials.from(user);
        ValidatableResponse responseAuth = UserAction.authorizationUser(userCredential);
        UserAssertion.assertSuccessfulAuthorization(responseAuth);
    }
    @Test
    public void errorAuthorizationUserWrongPassword() {
        UserCredentials userCredential = UserCredentials.wrongPassword(user);
        ValidatableResponse responseAuth = UserAction.authorizationUser(userCredential);
        UserAssertion.assertRequiredFieldsWrongFilledIn(responseAuth);
    }
    @Test
    public void errorAuthorizationUserWrongEmail() {
        UserCredentials userCredential = UserCredentials.wrongEmail(user);
        ValidatableResponse responseAuth = UserAction.authorizationUser(userCredential);
        UserAssertion.assertRequiredFieldsWrongFilledIn(responseAuth);
    }
    @Test
    public void errorAuthorizationUserWrongEmailAndPassword() {
        UserCredentials userCredential = UserCredentials.wrongEmailAndPassword(user);
        ValidatableResponse responseAuth = UserAction.authorizationUser(userCredential);
        UserAssertion.assertRequiredFieldsWrongFilledIn(responseAuth);
    }

}

