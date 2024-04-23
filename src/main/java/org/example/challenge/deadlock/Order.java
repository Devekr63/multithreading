package org.example.challenge.deadlock;

public record Order( int orderId,ShoeType shoeType,int quantity) {

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", shoeType=" + shoeType +
                ", quantity=" + quantity +
                '}';
    }
}
