package by.xenon28082.shop.entity;

public class Reserve {
    private long orderId;
    private long userId;
    private Product product;
    private long productId;
    private int amount;

    public Reserve(long userId, Product product, int amount) {
        this.userId = userId;
        this.product = product;
        this.amount = amount;
    }

    public Reserve(long userId, long productId, int amount) {
        this.userId = userId;
        this.productId = productId;
        this.amount = amount;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
