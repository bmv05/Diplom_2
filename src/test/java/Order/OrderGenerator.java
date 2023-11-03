package Order;

import io.restassured.http.ContentType;

import java.util.List;

import static io.restassured.RestAssured.given;

public class OrderGenerator {
    public static Order withIngredient() {
        Ingredients ingredients = getAllIngredients();
        return new Order(new String[]{ingredients.getData().get(0).get_id()});
    }

    public static Order withoutIngredient() {
        return new Order();
    }

    public static Order invalidHash() {
        return new Order(new String[]{"111c0c5a71d1f821234123123"});
    }

    public static Ingredients getAllIngredients() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .get("/api/ingredients")
                .body().as(Ingredients.class);
    }
}

