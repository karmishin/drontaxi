package xyz.karmishin.drontaxiweb.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime date;

    @ManyToOne
    private User user;

    private String departureAddress, destinationAddress;

    public enum Status {
        WAITING("Ожидание свободного дрона"),
        IN_PROGRESS("В процессе выполнения"),
        COMPLETE("Выполнен"),
        CANCELED("Отменён");

        public final String userFriendlyName;

        Status(String userFriendlyName) {
            this.userFriendlyName = userFriendlyName;
        }
    }

    private Status currentStatus;

    @ManyToOne
    private Drone drone;

    protected Order() {

    }

    public Order(User user, String departureAddress, String destinationAddress) {
        this.user = user;
        this.departureAddress = departureAddress;
        this.destinationAddress = destinationAddress;
        date = LocalDateTime.now();
        currentStatus = Status.WAITING;
    }

    public Long getId() {
        return id;
    }

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

    public Status getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Status currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }
}
