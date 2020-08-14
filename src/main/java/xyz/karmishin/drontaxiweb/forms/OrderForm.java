package xyz.karmishin.drontaxiweb.forms;

import xyz.karmishin.drontaxiweb.entities.Order;
import xyz.karmishin.drontaxiweb.entities.User;

import java.time.LocalDateTime;

public class OrderForm {
    private String departureAddress, destinationAddress;

    public Order toOrder(User user) {
        Order order = new Order();
        order.setDate(LocalDateTime.now());
        order.setDepartureAddress(departureAddress);
        order.setDestinationAddress(destinationAddress);
        order.setCurrentStatus(Order.Status.COMPLETE);
        order.setUser(user);

        return order;
    }

    public String getDepartureAddress() {
        return departureAddress;
    }

    public void setDepartureAddress(String departureAddress) {
        this.departureAddress = departureAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }
}
