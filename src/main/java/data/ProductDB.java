package data;

import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductDB {

    public static List<Product> getProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("86 (the band) - True Life Songs and Pictures", 14.95));
        productList.add(new Product("Paddlefoot - The first CD", 12.95));
        productList.add(new Product("Paddlefoot - The second CD", 14.95));
        productList.add(new Product("Joe Rut - Genuine Wood Grained Finish", 14.95));
        return productList;
    }

    public static Product getProduct(String description) {
        for (Product product : getProducts()) {
            if (product.getDescription().equals(description)) {
                return product;
            }
        }
        return null;
    }
}