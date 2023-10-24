/*Изменение данных пользователя:
        с авторизацией,
        без авторизации,
        Для обеих ситуаций нужно проверить, что любое поле можно изменить.
        Для неавторизованного пользователя — ещё и то, что система вернёт ошибку.*/


import User.User;
import User.UserGenerator;
import User.UserAction;
import User.UserAssertion;
import User.UserUpdate;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChangeDataUserTest {
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
    public void successfulUserUpdatePassword() {
        UserUpdate userChanges = UserUpdate.changePassword(user);
        ValidatableResponse responseUpdate = UserAction.updateUser(userChanges, accessToken);
        UserAssertion.assertSuccessfulUpdate(responseUpdate);
    }


    @Test
    public void successfulUserUpdateName() {
        UserUpdate userChanges = UserUpdate.changeName(user);
        ValidatableResponse responseUpdate = UserAction.updateUser(userChanges, accessToken);
        UserAssertion.assertSuccessfulUpdate(responseUpdate);
    }

    @Test
    public void successfulUserUpdateNameAndPassword() {
        UserUpdate userChanges = UserUpdate.changeNameAndPassword(user);
        ValidatableResponse responseUpdate = UserAction.updateUser(userChanges, accessToken);
        UserAssertion.assertSuccessfulUpdate(responseUpdate);
    }


    @Test
    public void errorUserUpdateEmail() {
        User secondUser = UserGenerator.randomUser();
        ValidatableResponse response = UserAction.createNewUser(secondUser);
        UserAssertion.assertSuccessfulCreation(response);
        String secondAccessToken = response.extract().path("accessToken");

        ValidatableResponse responseUpdate = UserAction.updateUserEmail(this.user, secondAccessToken);
        UserAssertion.assertErrorBecauseEmailExist(responseUpdate);

        ValidatableResponse deleteUser = UserAction.deleteCreatedUser(secondAccessToken);
        UserAssertion.assertUserDelete(deleteUser);

    }

    @Test
    public void errorUserUpdateNonAuthorization(){
    UserUpdate userChanges = UserUpdate.changeNameAndPassword(user);
        ValidatableResponse responseUpdate = UserAction.updateUserWithoutToken(userChanges);
        UserAssertion.assertErrorWithoutAuthorization(responseUpdate);

    }

}
