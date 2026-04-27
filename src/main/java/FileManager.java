import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static List<Product> getCart() {
        List<Product> cart = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader("src/main/resources/cart.csv");
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String sku = parts[0];
                String id = parts[1];
                double price = Float.parseFloat(parts[2]);
                String category = parts[3];

                Product carts = new Product(sku, id, price, category);
                cart.add(carts);
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("There was a problem reading the inventory file.");
        } catch (Exception ex) {
            System.out.println("Something went from with the file.");
        }

        return cart;
    }
    public static List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader("src/main/resources/products.csv");
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String sku = parts[0];
                String id = parts[1];
                double price = Float.parseFloat(parts[2]);
                String category = parts[3];

                Product product = new Product(sku, id, price, category);
                products.add(product);
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("There was a problem reading the inventory file.");
        } catch (Exception ex) {
            System.out.println("Something went from with the file.");
        }

        return products;
    }
    public static void writeProduct(Product product){
        try{
            File file = new File("src/main/resources/cart.csv");
            FileWriter fileWriter = new FileWriter(file, true);

            if (file.length() > 0) {
                fileWriter.write(System.lineSeparator());

            }

            fileWriter.write(String.format("%s|%s|%.2f|%s", product.getSku(), product.getId(), product.getPrice(), product.getCategory()));
            fileWriter.close();
        }
        catch(IOException ex){
            System.out.println("Error writing to file.");
        }
    }
    //This is gave me a hard time...I had to update the file as a whole
    public static void rewriteCart(List<Product> cartList) {
        try (FileWriter writer = new FileWriter("src/main/resources/cart.csv")) {

            for (Product product : cartList) {
                writer.write(product.getSku() + "|"
                        + product.getId() + "|"
                        + product.getPrice() + "|"
                        + product.getCategory() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error rewriting cart.csv");
        }
    }
}

