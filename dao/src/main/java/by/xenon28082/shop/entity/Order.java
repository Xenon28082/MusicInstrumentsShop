package by.xenon28082.shop.entity;

public class Order {
    private long orderId;
    private long userId;
    private Product product;
    private long productId;
    private int amount;
    private boolean isReserved;

    public Order(long userId, Product product, int amount, boolean isReserved) {
        this.userId = userId;
        this.product = product;
        this.amount = amount;
        this.isReserved = isReserved;

    }

    public Order(long userId, long productId, int amount) {
        this.userId = userId;
        this.productId = productId;
        this.amount = amount;
    }

    public Order(long orderId, long userId, long productId, int amount, boolean isReserved) {
        this.orderId = orderId;
        this.userId = userId;
        this.productId = productId;
        this.amount = amount;
        this.isReserved = isReserved;
    }

    public Order(){}

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

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    @Override
    public String toString() {
        return "Reserve{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", product=" + product +
                ", productId=" + productId +
                ", amount=" + amount +
                '}';
    }
}
