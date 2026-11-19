package model;

public class Product {
    private String description;
    private  double price;

    public Product (){
    }
    public Product(String description, double price){
        this.description= description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String discription){
        this.description = description;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price){
        this.price=price;
    }
}
