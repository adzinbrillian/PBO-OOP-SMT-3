import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

public class FastFoodSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Restaurant restaurant = new Restaurant();

        // Initial setup

        // Hire some employees
        Employee cook = new Employee("John", "Cooking");
        Employee server = new Employee("Alice", "Serving");
        restaurant.hireEmployee(cook);
        restaurant.hireEmployee(server);

        // Add inventory
        restaurant.addInventoryItem("Bun", 10);
        restaurant.addInventoryItem("Patty", 10);
        restaurant.addInventoryItem("Lettuce", 10);
        restaurant.addInventoryItem("Tomato", 10);
        restaurant.addInventoryItem("Fries", 5);

        // Create food items
        Food burger = new Food("Burger", 5.99, Arrays.asList("Bun", "Patty", "Lettuce", "Tomato"), 5);
        Food fries = new Food("Fries", 2.99, Arrays.asList("Fries"), 3);

        // Add multiple customers
        addCustomer(restaurant, "Bob", Arrays.asList(burger, fries), 5);
        addCustomer(restaurant, "Jane", Arrays.asList(fries), 3);
        addCustomer(restaurant, "Alice", Arrays.asList(burger), 4);

        boolean running = true;
        while (running) {
            System.out.println("\nWelcome to the Fast Food Restaurant Simulator!");
            System.out.println("--------------------------");
            System.out.println("|       Restaurant       |");
            System.out.println("--------------------------");
            System.out.println("--- Restaurant Actions ---");
            System.out.println("1. View Waiting Customers");
            System.out.println("2. Accept Order");
            System.out.println("3. Prepare Order");
            System.out.println("4. Serve Order");
            System.out.println("5. Complete Orders");
            System.out.println("6. View Status");
            System.out.println("7. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    restaurant.showWaitingCustomers();
                    break;
                case 2:
                    restaurant.showWaitingCustomers();
                    if (!restaurant.isQueueEmpty()) {
                        System.out.print("Select customer by number: ");
                        int customerIndex = scanner.nextInt();
                        restaurant.acceptOrder(customerIndex - 1);  // We subtract 1 because the list is 0-indexed
                    }
                    break;
                case 3:
                    restaurant.prepareOrder();
                    break;
                case 4:
                    restaurant.serveOrder();
                    break;
                case 5:
                    restaurant.completeOrders();
                    break;
                case 6:
                    System.out.println("Cash: $" + restaurant.getCash());
                    System.out.println("Reputation: " + restaurant.getReputation());
                    restaurant.showInventory();
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting the game...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }

    // Method to add customers to the restaurant queue
    private static void addCustomer(Restaurant restaurant, String name, List<Food> preferences, int patience) {
        Customer customer = new Customer(name, preferences, patience);
        restaurant.addCustomerToQueue(customer);
        // System.out.println("Customer " + name + " added to the queue.");
    }
}