import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] products = {"Chips", "Chocolate", "Water", "Soda"};
        double[] prices = {1.50, 2.30, 0.60, 1.40};
        int[] initialQuantity = {5, 5, 5, 5};
        int[] quantity = initialQuantity.clone();

        double totalSales = 0;
        int choice;

        do {
            displayMenu();
            System.out.println("* * *");
            System.out.print("Choose a number or press 0 to exit: ");
            System.out.println(" ");
            choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("Total sales: " + totalSales + " BGN");
                    break;
                case 1:
                    availableItems(quantity);
                    break;
                case 2:
                    totalSales += purchaseItem(scanner, products, prices, quantity);
                    break;
                case 3:
                    refillInventory(quantity, initialQuantity);
                    System.out.println("");
                    break;
                case 4:
                    checkInventory(products, quantity);
                    System.out.println(" ");
                    break;
                default:
                    System.out.println("Please insert a number from 1 to 4.");
                    break;
            }

        } while (choice != 0);
    }

    public static void availableItems(int[] quantity) {
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Available items:");
        System.out.println(" Code  |  Name       |  Price  |  Quantity ");
        System.out.println("-------------------------------------------");
        System.out.println(" 1     |  Chips      |  1.50   |  " + quantity[0]);
        System.out.println(" 2     |  Chocolate  |  2.30   |  " + quantity[1]);
        System.out.println(" 3     |  Water      |  0.60   |  " + quantity[2]);
        System.out.println(" 4     |  Soda       |  1.40   |  " + quantity[3]);
        System.out.println(" ");
    }

    public static void displayMenu() {
        System.out.println("1. Display Available Items.");
        System.out.println("2. Purchase Item.");
        System.out.println("3. Refill Inventory.");
        System.out.println("4. Check Inventory.");
        System.out.println(" ");
    }

    public static double purchaseItem(Scanner scanner, String[] products, double[] prices, int[] quantity) {
        double totalSales = 0;
        System.out.print("Enter the product code (1-4): ");
        int choice = scanner.nextInt();
        if (choice < 1 || choice > 4) {
            System.out.println("Invalid product code. Please enter a number (1-4).");
            System.out.println("");
            return totalSales;
        }
        if (quantity[choice - 1] == 0) {
            System.out.println("Sorry, " + products[choice - 1] + " is out of stock.");
            return totalSales;
        }
        System.out.println("Product chosen: " + products[choice - 1]);
        System.out.print("Insert money (BGN): ");
        double money = scanner.nextDouble();
        System.out.println(" ");

        if (money >= prices[choice - 1]) {
            System.out.println("...Dispensing " + products[choice - 1] + "...");
            System.out.println("Change: " + (money - prices[choice - 1]) + " BGN");
            System.out.println("Take your product...");
            System.out.println("");
            quantity[choice - 1]--;
            totalSales = prices[choice - 1];
        } else {
            System.out.println("You don't have enough money to buy this product.");
            System.out.println(" ");
        }
        return totalSales;
    }

    public static void refillInventory(int[] quantity, int[] initialQuantity) {
        System.arraycopy(initialQuantity, 0, quantity, 0, initialQuantity.length);
        System.out.println("~Inventory has been refilled.~");
    }

    public static void checkInventory(String[] products, int[] quantity) {
        System.out.println("Vending machine stock:");
        for (int i = 0; i < products.length; i++) {
            System.out.println(products[i] + ": " + quantity[i]);
        }
    }
}