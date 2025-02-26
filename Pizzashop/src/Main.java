import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Pizza shop class
class PizzaShop {
    // Shop name
    private String shopName;
    // Address
    private String address;
    // Email
    private String email;
    // Phone number
    private String phone;
    // Menu, key is pizza name, value is price
    private Map<String, Double> menu;
    // Toppings list
    private List<String> toppings;
    // Side dishes list
    private List<String> sideDishes;
    // Beverages list
    private List<String> beverages;
    // Order ID counter
    private int orderIdCounter;

    public PizzaShop(String shopName, String address, String email, String phone) {
        this.shopName = shopName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.menu = new HashMap<>();
        this.toppings = new ArrayList<>();
        this.sideDishes = new ArrayList<>();
        this.beverages = new ArrayList<>();
        this.orderIdCounter = 1;
    }

    // Add pizza to the menu
    public void addPizzaToMenu(String pizzaName, double price) {
        menu.put(pizzaName, price);
    }

    // Add topping
    public void addTopping(String topping) {
        toppings.add(topping);
    }

    // Add side dish
    public void addSideDish(String sideDish) {
        sideDishes.add(sideDish);
    }

    // Add beverage
    public void addBeverage(String beverage) {
        beverages.add(beverage);
    }

    // Take an order
    public Order takeOrder(List<String> pizzas, List<String> sides, List<String> drinks) {
        int orderId = orderIdCounter++;
        double totalPrice = 0;

        // Calculate the total price of pizzas
        for (String pizza : pizzas) {
            if (menu.containsKey(pizza)) {
                totalPrice += menu.get(pizza);
            }
        }

        // Here we can add price logic for side dishes and beverages. For simplicity, assume fixed prices for side dishes and beverages.
        totalPrice += sides.size() * 5;
        totalPrice += drinks.size() * 3;

        return new Order(orderId, pizzas, sides, drinks, totalPrice);
    }

    // Make pizzas
    public void makePizza(List<String> pizzas) {
        System.out.println("Making the following pizzas:");
        for (String pizza : pizzas) {
            System.out.println("- " + pizza);
        }
    }
    public void showReceipt(Order order){
        order.printReceipt(shopName, menu);
    }
}    
// Order class
class Order {
    private String orderID;
    private List<String> pizzas;
    private List<String> sideDishes;
    private List<String> beverages;
    private double orderTotal;
    private String pizzaIngredients;
    private static final String DEF_ORDER_ID = "DEF-SOH-099";
    private static final String DEF_PIZZA_INGREDIENTS = "Mozzarella Cheese";
    private static final double DEF_ORDER_TOTAL = 15.00;

    public Order() {
        this.orderID = DEF_ORDER_ID;
        this.pizzaIngredients = DEF_PIZZA_INGREDIENTS;
        this.orderTotal = DEF_ORDER_TOTAL;
        this.sideDishes = new ArrayList<>();
        this.beverages = new ArrayList<>();
        this.pizzas = new ArrayList<>();
    }
    public Order(String orderID, List<String> pizzas, List<String> sideDishes, List<String> beverages, double orderTotal){
        this.orderID = orderID;
        this.pizzas = pizzas;
        this.sideDishes = sideDishes;
        this.beverages = beverages;
        this.orderTotal = orderTotal;
        this.pizzaIngredients = DEF_PIZZA_INGREDIENTS;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public List<String> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<String> pizzas) {
        this.pizzas = pizzas;
    }

    public List<String> getSideDishes() {
        return sideDishes;
    }

    public void setSideDishes(List<String> sideDishes){
        this.sideDishes = sideDishes;
    }

    public List<String> getBeverages(){
        return beverages;
    }

    public void setBeverages(List<String> beverages){
        this.beverages = beverages;
    }

    public double getOrderTotal(){
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal){
        this.orderTotal = orderTotal;
    }

    public String getPizzaIngredients(){
        return pizzaIngredients;
    }

    public void setPizzaIngredients(String pizzaIngredients){
        this.pizzaIngredients = pizzaIngredients;
    }

    private void printReceipt(String shopName, Map<String, Double> menu){
        System.out.println("--- " + shopName + "Receipt ---");
        System.out.println("Order Id: " + orderID);
        System.out.println("Pizzas: ");
        for (String pizza : pizzas){
            System.out.println("- " + pizza + ": $" + menu.get(pizza));            
        }
        System.out.println("Side Dishes");
        for (String side : sideDishes){
            System.out.println("- " + side + ": $5");
        }
        System.out.println("Beverages: ");
        for (String drink : beverages){
            System.out.println("- " + drink + ": $3");
        }
        System.out.println("Total Price: $" + orderTotal); 
    }    
}

// Main class for testing
public class Main {
    public static void main(String[] args) {
        // Create a pizza shop object
        PizzaShop pizzaShop = new PizzaShop("Slice-o-Heaven", "123 Pizza St", "info@sliceoheaven.com", "555 - 1234");

        // Add pizzas to the menu
        pizzaShop.addPizzaToMenu("Cheese Pizza", 10.0);
        pizzaShop.addPizzaToMenu("Ham Pizza", 12.0);

        // Add toppings
        pizzaShop.addTopping("Mushrooms");
        pizzaShop.addTopping("Green Peppers");

        // Add side dishes
        pizzaShop.addSideDish("French Fries");
        pizzaShop.addSideDish("Chicken Wings");

        // Add beverages
        pizzaShop.addBeverage("Coke");
        pizzaShop.addBeverage("Sprite");

        // Simulate taking an order
        List<String> pizzas = new ArrayList<>();
        pizzas.add("Cheese Pizza");
        pizzas.add("Ham Pizza");
        List<String> sides = new ArrayList<>();
        sides.add("French Fries");
        List<String> drinks = new ArrayList<>();
        drinks.add("Coke");

        Order order = pizzaShop.takeOrder(pizzas, sides, drinks);

        // Make pizzas
        pizzaShop.makePizza(pizzas);

        // Print receipt
        pizzaShop.printReceipt(order);
    }
}