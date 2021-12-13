package by.xenon28082.shop.entity;

import java.util.List;

public class FinalOrder {

    private List<Order> orders;
    private long finalOrderId;
    private long userId;
    private boolean isAccepted;
    private boolean isRefused;
    private boolean isClosed;



    public FinalOrder(long finalOrderId, long userId, boolean isAccepted, boolean isRefused, boolean isClosed, List<Order> orders) {
        this.orders = orders;
        this.finalOrderId = finalOrderId;
        this.userId = userId;
        this.isAccepted = isAccepted;
        this.isRefused = isRefused;
        this.isClosed = isClosed;
    }

    public FinalOrder(long finalOrderId, long userId, boolean isAccepted, boolean isRefused, boolean isClosed) {
        this.orders = orders;
        this.userId = userId;
        this.isAccepted = isAccepted;
        this.isRefused = isRefused;
        this.isClosed = isClosed;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public FinalOrder() {
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public boolean isRefused() {
        return isRefused;
    }

    public void setRefused(boolean refused) {
        isRefused = refused;
    }

    public List<Order> getProducts() {
        return orders;
    }

    public void setProducts(List<Order> orders) {
        this.orders = orders;
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

    @Override
    public String toString() {
        return "FinalOrder{" +
                "orders=" + orders +
                ", finalOrderId=" + finalOrderId +
                ", userId=" + userId +
                ", isAccepted=" + isAccepted +
                ", isRefused=" + isRefused +
                '}';
    }
}
