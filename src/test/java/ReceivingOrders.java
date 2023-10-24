import Order.Order;
import Order.OrderGenerator;
import User.User;
import User.UserGenerator;
import User.UserAction;
import Order.OrderAction;
import Order.OrderAssertion;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

public class ReceivingOrders {
    private String accessToken;
    User user = UserGenerator.randomUser();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
    }

    @Test
    public void receivingListOrderWithAuthorization() {
        Order order = OrderGenerator.withOneIngredient();
        accessToken = UserAction.createNewUser(user).extract().path("accessToken");
        for (int i = 1; i <= 5; i++) {
            OrderAction.createNewOrderWithAuthorization(order, accessToken);
        }
        ValidatableResponse response = OrderAction.showOrdersWithAuthorization(accessToken);
        OrderAssertion.assertShowOrdersWithAuthorization(response);
    }
    @Test
    public void receivingListOrderWithoutAuthorization() {
        Order order = OrderGenerator.withOneIngredient();
        accessToken = UserAction.createNewUser(user).extract().path("accessToken");
        for (int i = 1; i <= 5; i++) {
            OrderAction.createNewOrderWithAuthorization(order, accessToken);
        }
        ValidatableResponse response = OrderAction.showOrdersWithoutAuthorization();
        OrderAssertion.assertErrorShowOrdersWithoutAuthorization(response);
    }


}
