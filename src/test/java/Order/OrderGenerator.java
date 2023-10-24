package Order;

public class OrderGenerator {
    public static Order withOneIngredient() {
        return new Order(new String[]{"61c0c5a71d1f82001bdaaa6d"});
    }
    public static Order withOThreeIngredient() {
        return new Order(new String[]{"61c0c5a71d1f82001bdaaa6d", "61c0c5a71d1f82001bdaaa77", "61c0c5a71d1f82001bdaaa7a"});
    }
    public static Order withoutIngredient() {
        return new Order();
    }
    public static Order invalidHash() {
        return new Order(new String[]{"111c0c5a71d1f821234123123"});
    }
}

