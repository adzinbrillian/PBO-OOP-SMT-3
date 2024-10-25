public class Order {
    private Customer customer;
    private Food food;
    private boolean isPrepared;
    private boolean isServed;

    public Order(Customer customer, Food food) {
        this.customer = customer;
        this.food = food;
        this.isPrepared = false;
        this.isServed = false;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Food getFood() {
        return food;
    }

    public boolean isPrepared() {
        return isPrepared;
    }

    public void setPrepared(boolean isPrepared) {
        this.isPrepared = isPrepared;
    }

    public boolean isServed() {
        return isServed;
    }

    public void setServed(boolean isServed) {
        this.isServed = isServed;
    }

    @Override
    public String toString() {
        return "Order for " + customer.getName() + ": " + food.getName();
    }
}