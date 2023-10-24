package Order;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderAction {
    public static ValidatableResponse createNewOrderWithAuthorization(Order order, String accessToken) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .header("authorization", accessToken)
                .body(order)
                .when()
                .post("/api/orders")
                .then().log().all();
    }
    public static ValidatableResponse createNewOrderWithoutAuthorization(Order order) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(order)
                .when()
                .post("/api/orders")
                .then().log().all();
    }
}
