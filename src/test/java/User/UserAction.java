package User;

import com.fasterxml.jackson.core.Base64Variant;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.ValidatableResponse;

import java.util.Map;

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
    public static ValidatableResponse deleteCreatedUser(String accessToken) {
        return given().log().all()
                .header("authorization", accessToken)
                .when()
                .delete("/api/auth/user")
                .then().log().all();
    }

}
