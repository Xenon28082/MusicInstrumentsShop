package by.xenon28082.shop.entity;

import java.util.List;

public class FinalOrder {

    private List<Product> products;
    private long finalOrderId;
    private long userId;

    public FinalOrder(List<Product> products, long finalOrderId, long userId) {
        this.products = products;
        this.finalOrderId = finalOrderId;
        this.userId = userId;
    }

    public FinalOrder() {
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public long getFinalOrderId() {
        return finalOrderId;
    }

    public void setFinalOrderId(long finalOrderId) {
        this.finalOrderId = finalOrderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
