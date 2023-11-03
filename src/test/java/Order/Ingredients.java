package Order;

import java.util.List;

public class Ingredients {
    boolean success;
    private List<Ingredient> data;

    public Ingredients(boolean success, List<Ingredient> data) {
        this.success = success;
        this.data = data;
    }

    public Ingredients() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Ingredient> getData() {
        return data;
    }

    public void setData(List<Ingredient> data) {
        this.data = data;
    }
}