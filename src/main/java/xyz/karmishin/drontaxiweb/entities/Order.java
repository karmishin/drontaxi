package xyz.karmishin.drontaxiweb.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    @ManyToOne
    private User user;
    @ManyToOne
    private User operator;
    private String departureAddress, destinationAddress;
    private enum Status { EN_ROUTE, WAITING, COMPLETE, CANCELLED };
    private Status currentStatus;
    @ManyToOne
    @JoinColumn(name = "drone_id")
    private Drone drone;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getOperator() {
        return operator;
    }

    public void setOperator(User operator) {
        this.operator = operator;
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

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public Status getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Status currentStatus) {
        this.currentStatus = currentStatus;
    }
}
