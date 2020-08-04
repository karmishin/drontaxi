package xyz.karmishin.drontaxiweb.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
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
}
