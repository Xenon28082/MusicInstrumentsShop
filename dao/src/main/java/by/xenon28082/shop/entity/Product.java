package by.xenon28082.shop.entity;

public class Product {

    private long id;
    private String name;
    private double price;
    private long stock;
    private String type;
    private String vendor;

    public Product(long id, String name, double price, long stock, String type, String vendor) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.type = type;
        this.vendor = vendor;
    }

    public Product(String name, double price, long stock, String type, String vendor) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.type = type;
        this.vendor = vendor;
    }

    public Product(){

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public long getStock() {
        return stock;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
}
