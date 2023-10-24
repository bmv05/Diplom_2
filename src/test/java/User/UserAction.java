package User;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserAction {
    public static ValidatableResponse createNewUser(User user) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/api/auth/register")
                .then().log().all();
    }
    public static ValidatableResponse authorizationUser(UserCredentials userCredential) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(userCredential)
                .when()
                .post("/api/auth/login")
                .then().log().all();
    }
    public static ValidatableResponse deleteCreatedUser(String accessToken) {
        return given().log().all()
                .header("authorization", accessToken)
                .when()
                .delete("/api/auth/user")
                .then().log().all();
    }
    public static ValidatableResponse updateUser(UserUpdate userUpdate,String accessToken) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .header("authorization", accessToken)
                .body(userUpdate)
                .when()
                .patch("/api/auth/user")
                .then().log().all();
    }
    public static ValidatableResponse updateUserWithoutToken(UserUpdate userUpdate) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(userUpdate)
                .when()
                .patch("/api/auth/user")
                .then().log().all();
    }
    public static ValidatableResponse updateUserEmail(User user,String accessToken) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .header("authorization", accessToken)
                .body(user)
                .when()
                .patch("/api/auth/user")
                .then().log().all();
    }

}
