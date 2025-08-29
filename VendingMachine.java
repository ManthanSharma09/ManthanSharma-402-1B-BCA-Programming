import java.util.*;

class Item {
    String name;
    int price;
    int stock;

    Item(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return name + " - ₹" + price + " (Stock: " + stock + ")";
    }
}

public class VendingMachine {
    private static Map<Integer, Item> inventory = new HashMap<>();
    private static Map<String, Integer> cart = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize inventory
        inventory.put(1, new Item("Coke", 50, 5));
        inventory.put(2, new Item("Chips", 30, 3));
        inventory.put(3, new Item("Water", 20, 10));
        inventory.put(4, new Item("Chocolate", 40, 2));

        System.out.println("=== Welcome to Virtual Vending Machine ===");

        boolean running = true;
        while (running) {
            System.out.println("\n1. View Items");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = getValidInt();

            switch (choice) {
                case 1 -> displayItems();
                case 2 -> addToCart();
                case 3 -> viewCart();
                case 4 -> checkout();
                case 5 -> {
                    System.out.println("Thank you for using Vending Machine!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void displayItems() {
        System.out.println("\nAvailable Items:");
        for (Map.Entry<Integer, Item> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }

    private static void addToCart() {
        displayItems();
        System.out.print("Enter item number: ");
        int id = getValidInt();

        if (!inventory.containsKey(id)) {
            System.out.println("Invalid item.");
            return;
        }

        Item item = inventory.get(id);
        if (item.stock <= 0) {
            System.out.println("Sorry, " + item.name + " is out of stock.");
            return;
        }

        System.out.print("Enter quantity: ");
        int qty = getValidInt();

        if (qty <= 0) {
            System.out.println("Invalid quantity.");
            return;
        }
        if (qty > item.stock) {
            System.out.println("Only " + item.stock + " available.");
            return;
        }

        cart.put(item.name, cart.getOrDefault(item.name, 0) + qty);
        item.stock -= qty;
        System.out.println(qty + " x " + item.name + " added to cart.");
    }

    private static void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("\nCart is empty.");
            return;
        }
        System.out.println("\nYour Cart:");
        int total = 0;
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            Item item = getItemByName(entry.getKey());
            int cost = item.price * entry.getValue();
            total += cost;
            System.out.println(entry.getKey() + " x " + entry.getValue() + " = ₹" + cost);
        }
        System.out.println("Total: ₹" + total);
    }

    private static void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }

        int total = calculateTotal();
        System.out.println("Your total is ₹" + total);
        int inserted = 0;

        while (inserted < total) {
            System.out.print("Insert money (₹50, ₹100, etc): ");
            int money = getValidInt();
            if (money <= 0) {
                System.out.println("Invalid amount.");
                continue;
            }
            inserted += money;
            System.out.println("Total inserted: ₹" + inserted);
        }

        if (inserted >= total) {
            int change = inserted - total;
            System.out.println("\nDispensing items...");
            for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                System.out.println("✔ " + entry.getValue() + " x " + entry.getKey());
            }
            if (change > 0) {
                System.out.println("Returning change: ₹" + change);
            }
            cart.clear();
        }
    }

    private static int calculateTotal() {
        int total = 0;
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            Item item = getItemByName(entry.getKey());
            total += item.price * entry.getValue();
        }
        return total;
    }

    private static Item getItemByName(String name) {
        for (Item item : inventory.values()) {
            if (item.name.equals(name)) return item;
        }
        return null;
    }

    private static int getValidInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Invalid input. Enter a number: ");
            sc.next();
        }
        return sc.nextInt();
    }
}
