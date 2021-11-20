package by.xenon28082.shop.dao.impl.comparators;

import by.xenon28082.shop.entity.Order;

import java.util.Comparator;

public class OrderIdComparator implements Comparator<Order> {


    @Override
    public int compare(Order o1, Order o2) {
        if(o1.getOrderId() > o2.getOrderId()){
            return 1;
        }else if(o1.getOrderId() < o2.getOrderId()){
            return -1;
        }
        return 0;
    }
}
