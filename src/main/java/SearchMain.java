
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class SearchMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Product> products = FileManager.getProducts();
        List<Product> carts = FileManager.getCart();


        outerLoop:
        // Had to research how to break out of a while loop without exiting the system and came across outerLoop
        while (true) {
            while (true) {
                System.out.println("\n=== Video Game Store Menu ===");
                System.out.println("1. Display Product");
                System.out.println("2. Display Cart");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        displayProducts(products);
                        System.out.println("\n=== Video Game Store Menu ===");
                        System.out.println("1. Search by ID || Item || Price || Category");
                        System.out.println("2. Add a product to their cart");
                        System.out.println("3. Go back to homepage");
                        int nextChoice = Integer.parseInt(scanner.nextLine());
                        while (true) {
                            switch (nextChoice) {
                                case 1:

                                    System.out.println("Search the product by input");
                                    System.out.println("1. Product ID/SKU");
                                    System.out.println("2. Product Name");
                                    System.out.println("3. Product Price");
                                    System.out.println("4. Product Category");
                                    System.out.println("5. Go back to homepage");
                                    int userChoice = Integer.parseInt(scanner.nextLine());
                                    switch (userChoice) {
                                        case 1:
                                            System.out.println("Please type the Product ID/SKU");
                                            String userChoiceID = scanner.nextLine();
                                            userChoiceID = userChoiceID.toUpperCase();
                                            getProductById(products, userChoiceID);
                                            break;
                                        case 2:
                                            System.out.println("Please type the Product Name");
                                            String userChoiceName = scanner.nextLine();
                                            getProductByName(products, userChoiceName);
                                            break;
                                        case 3:
                                            System.out.println("Please type the Product Price Range");
                                            System.out.println("Min: ");
                                            int userChoicePrice1 = Integer.parseInt(scanner.nextLine());
                                            System.out.println("Max: ");
                                            int userChoicePrice2 = Integer.parseInt(scanner.nextLine());
                                            if (userChoicePrice1 > userChoicePrice2) {
                                                System.out.println("Min can't be more than max!");
                                                break;
                                            } else getProductByPriceRange(products, userChoicePrice1, userChoicePrice2);
                                            break;
                                        case 4:
                                            System.out.println("Please type the Product Category");
                                            String userChoiceCate = scanner.nextLine();
                                            getProductByCate(products, userChoiceCate);
                                            break;
                                        case 5:
                                            continue outerLoop;
                                        default:
                                            System.out.println("Found : Error 404");
                                    }
                                    break;
                                case 2:
                                    addProduct(scanner);
                                    continue outerLoop;
                                case 3:
                                    continue outerLoop;
                            }
                        }
                    case 2:
                        displayCart();
                        System.out.println("-----------");
                        System.out.println("1. Check Out");
                        System.out.println("2. Remove Product from the cart");
                        System.out.println("3. Go back to homepage");
                        int choice2 = Integer.parseInt(scanner.nextLine());
                        switch (choice2) {
                            case 1:
                                checkOut(carts);
                                continue outerLoop;
                            case 2:
                                removeItemCart(scanner);
                                continue outerLoop;
                            case 3:
                                continue outerLoop;
                        }

                    case 3:
                        System.exit(0);
                    default:
                        System.out.println("Found : Error 404");

                }
            }
        }
    }

    public static void displayProducts(List<Product> products) {
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }

    public static void displayCart() {
        List<Product> cart = FileManager.getCart();
        for (Product product : cart) {
            System.out.println(product.toString());
        }
    }

    public static void getProductById(List<Product> products, String sku) {
        for (Product product : products) {
            if (Objects.equals(product.getSku(), sku)) {
                System.out.println(product.toString());
                return;
            }
        }
    }

    public static void getProductByName(List<Product> products, String name) {
        for (Product product : products) {
            if (Objects.equals(product.getId(), name)) {
                System.out.println(product.toString());
                return;
            }
        }
    }

    public static void getProductByPriceRange(List<Product> products, int minPrice, int maxPrice) {
        for (Product product : products) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                System.out.println(product.toString());
            }
        }
    }

    public static void getProductByCate(List<Product> products, String Cate) {
        for (Product product : products) {
            if (product.getCategory().equals(Cate)) {
                System.out.println(product.toString());
            }
        }
    }

    public static void addProduct(Scanner scanner) {
        System.out.println("What is the Product ID/SKU?");
        String productId = scanner.nextLine();

        System.out.println("What is the Product Name?");
        String productName = scanner.nextLine();

        System.out.println("What is the Product Price?");
        float productPrice = Float.parseFloat(scanner.nextLine());

        System.out.println("What is the Product Category?");
        String productCate = scanner.nextLine();
        Product product = new Product(productId, productName, productPrice, productCate);
        FileManager.writeProduct(product);
    }

    public static void removeItemCart(Scanner scanner) {
        List<Product> cartList = FileManager.getCart();

        System.out.println("Enter the Product ID/SKU you want to remove:");
        String sku = scanner.nextLine().toUpperCase();

        for (int i = 0; i < cartList.size(); i++) {
            if (cartList.get(i).getSku().equalsIgnoreCase(sku)) {
                cartList.remove(i);
                break;
            }
        }
            FileManager.rewriteCart(cartList);
            System.out.println("Product removed from cart.");
    }
    public static void checkOut(List<Product> cartList) {
        List<Product> cart1 = FileManager.getCart();
        Scanner scanner = new Scanner(System.in);
        LocalDateTime now = LocalDateTime.now(); // had to research this
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(now.format(formatter));
        double carts = 0;
        for (Product cart : cart1) {
            carts += cart.getPrice();
        }
        System.out.printf("Your total price is : %.2f", carts);
        System.out.println(" Do you want to proceed to buy?" + "\n");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int userInput = scanner.nextInt();
        if(userInput == 1) {
            System.out.println("How much money do you want to give to the cashier?");
            double userMoney = scanner.nextDouble();
            if (userMoney < carts) {
                System.out.println("Not enought money!");
            } else {
                double exchangedTotal = userMoney - carts;
                System.out.printf("Current Date and Time: %s %n", now.format(formatter));
                System.out.println("Your Shopping Cart: " + cart1.toString());
                System.out.println("Total amount: " + carts + "\n");
                System.out.println("Amount Paid: " + userMoney + "\n");
                System.out.println("Change Given: " + (exchangedTotal));
                cartList.clear();
                FileManager.rewriteCart(cartList);
                }
            }
        }
    }

