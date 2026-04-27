import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Product {
    private String sku;
    private String id;
    private double price;
    private String category;

    public Product(String sku, String id, double price, String category) {
        this.sku = sku;
        this.id = id;
        this.price = price;
        this.category = category;
    }

    public Product() {

    }

    public String getSku(){
        return sku;
    }
    public void setSku(String sku){
        this.sku = sku;
    }
    public String getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("ID: %s Item: %s Price: %.2f Category: %s %n", sku, id, price, category);


    }
}
