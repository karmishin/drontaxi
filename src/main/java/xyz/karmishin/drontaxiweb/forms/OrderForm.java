package xyz.karmishin.drontaxiweb.forms;

import xyz.karmishin.drontaxiweb.entities.Order;
import xyz.karmishin.drontaxiweb.entities.User;

import javax.validation.constraints.NotBlank;

public class OrderForm {

    @NotBlank(message = "Поле адреса отправления должно быть заполнено.")
    private String departureAddress;
    @NotBlank(message = "Поле адреса назначения должно быть заполнено.")
    private String destinationAddress;

    public Order toOrder(User user) {
        return new Order(user, departureAddress, destinationAddress);
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
