package xyz.karmishin.drontaxiweb.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String manufacturer, model, licensePlate;
    private LocalDateTime productionDate, registrationDate, writeOffDate;
    @OneToMany(mappedBy = "drone")
    private List<Order> orders;
}
