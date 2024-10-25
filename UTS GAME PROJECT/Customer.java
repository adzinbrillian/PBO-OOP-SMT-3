import java.util.List;

public class Customer {
    private String name;
    private List<Food> foodPreferences;
    private int patience;

    public Customer(String name, List<Food> foodPreferences, int patience) {
        this.name = name;
        this.foodPreferences = foodPreferences;
        this.patience = patience;
    }

    public String getName() {
        return name;
    }

    public List<Food> getFoodPreferences() {
        return foodPreferences;
    }

    public int getPatience() {
        return patience;
    }

    public Food orderFood() {
        // In a real scenario, we could have more logic to select a specific food item from preferences
        return foodPreferences.get(0); // Assume customer orders the first item in their preference list
    }
}