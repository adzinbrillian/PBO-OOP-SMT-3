import java.util.List;

public class Food {
    private String name;
    private double price;
    private List<String> ingredients;
    private int cookTime;

    public Food(String name, double price, List<String> ingredients, int cookTime) {
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
        this.cookTime = cookTime;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public int getCookTime() {
        return cookTime;
    }

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}