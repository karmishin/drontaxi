package xyz.karmishin.drontaxiweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.karmishin.drontaxiweb.entities.Order;
import xyz.karmishin.drontaxiweb.entities.User;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
