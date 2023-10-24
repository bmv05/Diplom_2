import User.User;
import Order.Order;
import User.UserGenerator;
import User.UserAction;
import Order.OrderAction;
import Order.OrderGenerator;
import Order.OrderAssertion;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

public class CreateOrderTest {
    private String accessToken;
    User user = UserGenerator.randomUser();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
    }

    @Test
    public void createOrderWithAuthorization() {
        Order order = OrderGenerator.withOneIngredient();
        accessToken = UserAction.createNewUser(user).extract().path("accessToken");
        ValidatableResponse response = OrderAction.createNewOrderWithAuthorization(order, accessToken);
        OrderAssertion.assertSuccessfulOrderCreation(response);
    }

    @Test
    public void createOrderWithoutAuthorization() {
        Order order = OrderGenerator.withOThreeIngredient();
        ValidatableResponse response = OrderAction.createNewOrderWithoutAuthorization(order);
        OrderAssertion.assertSuccessfulOrderCreation(response);
    }


    @Test
    public void createOrderWithoutIngredients() {
        Order order = OrderGenerator.withoutIngredient();
        ValidatableResponse response = OrderAction.createNewOrderWithoutAuthorization(order);
        OrderAssertion.assertErrorOrderCreationWithoutIngredient(response);
    }


    @Test
    public void createOrderWithWrongHash() {
        Order order = OrderGenerator.invalidHash();
        ValidatableResponse response = OrderAction.createNewOrderWithoutAuthorization(order);
        OrderAssertion.assertInternalServerError(response);
    }
}
