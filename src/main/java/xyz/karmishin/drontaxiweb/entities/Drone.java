package xyz.karmishin.drontaxiweb.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String manufacturer, model, licensePlate;
    private LocalDateTime productionDate, registrationDate, writeOffDate;
    @OneToMany(mappedBy = "drone")
    private List<Order> orders;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public LocalDateTime getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDateTime productionDate) {
        this.productionDate = productionDate;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDateTime getWriteOffDate() {
        return writeOffDate;
    }

    public void setWriteOffDate(LocalDateTime writeOffDate) {
        this.writeOffDate = writeOffDate;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
