import java.util.*;

public class Restaurant {
    private List<Employee> employees;
    private List<Customer> customersQueue;
    private List<Order> orderList; // List of orders that are being processed
    private double cash;
    private Map<String, Integer> inventory;
    private int reputation;

    public Restaurant() {
        this.employees = new ArrayList<>();
        this.customersQueue = new ArrayList<>();
        this.orderList = new ArrayList<>();
        this.cash = 0.0;
        this.inventory = new HashMap<>();
        this.reputation = 100; // Start with 100 reputation
    }

    public void hireEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addCustomerToQueue(Customer customer) {
        customersQueue.add(customer);
    }

    public void addInventoryItem(String item, int quantity) {
        inventory.put(item, inventory.getOrDefault(item, 0) + quantity);
    }

    public void showWaitingCustomers() {
        if (customersQueue.isEmpty()) {
            System.out.println("No customers waiting.");
            return;
        }

        System.out.println("\n--- Waiting Customers ---");
        for (int i = 0; i < customersQueue.size(); i++) {
            System.out.println((i + 1) + ". " + customersQueue.get(i).getName());
        }
    }

    public void acceptOrder(int customerIndex) {
        if (customersQueue.isEmpty()) {
            System.out.println("No customers in the queue.");
            return;
        }

        if (customerIndex < 0 || customerIndex >= customersQueue.size()) {
            System.out.println("Invalid customer selection.");
            return;
        }

        Customer customer = customersQueue.get(customerIndex);  // Get selected customer
        Food orderedFood = customer.orderFood();  // Get their order

        if (canPrepareFood(orderedFood)) {
            Order newOrder = new Order(customer, orderedFood);
            orderList.add(newOrder);  // Add the order to the list
            customersQueue.remove(customerIndex); // Remove the customer from the queue
            System.out.println("Order accepted: " + newOrder);
        } else {
            System.out.println("Not enough ingredients to prepare " + orderedFood.getName() + " for " + customer.getName());
            reputation -= 10;  // Decrease reputation
        }
    }

    public void prepareOrder() {
        for (Order order : orderList) {
            if (!order.isPrepared()) {
                Food food = order.getFood();
                Employee cook = findAvailableEmployeeWithSkill("Cooking");
                
                if (cook != null) {
                    cook.setAvailable(false);
                    System.out.println("Cooking " + food.getName() + " for " + order.getCustomer().getName());
                    order.setPrepared(true);
                    decreaseInventory(food);
                    cook.setAvailable(true);
                } else {
                    System.out.println("No available cooks.");
                }
                return;  // Prepare one order at a time
            }
        }
        System.out.println("No orders to prepare.");
    }

    public void serveOrder() {
        for (Order order : orderList) {
            if (order.isPrepared() && !order.isServed()) {
                Employee server = findAvailableEmployeeWithSkill("Serving");
                if (server != null) {
                    server.setAvailable(false);
                    System.out.println("Serving " + order.getFood().getName() + " to " + order.getCustomer().getName());
                    order.setServed(true);
                    cash += order.getFood().getPrice();
                    server.setAvailable(true);
                    return;
                } else {
                    System.out.println("No available servers.");
                }
            }
        }
        System.out.println("No prepared orders to serve.");
    }

    public void completeOrders() {
        orderList.removeIf(Order::isServed);
        System.out.println("Completed all served orders.");
    }

    private boolean canPrepareFood(Food food) {
        for (String ingredient : food.getIngredients()) {
            if (!inventory.containsKey(ingredient) || inventory.get(ingredient) <= 0) {
                return false;
            }
        }
        return true;
    }

    private void decreaseInventory(Food food) {
        for (String ingredient : food.getIngredients()) {
            inventory.put(ingredient, inventory.get(ingredient) - 1);
        }
    }

    private Employee findAvailableEmployeeWithSkill(String skill) {
        for (Employee employee : employees) {
            if (employee.isAvailable() && employee.getSkill().equals(skill)) {
                return employee;
            }
        }
        return null;
    }

    public boolean isQueueEmpty() {
        return customersQueue.isEmpty();
    }

    public double getCash() {
        return cash;
    }

    public int getReputation() {
        return reputation;
    }

    public void showInventory() {
        System.out.println("Current Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}