package xyz.karmishin.drontaxiweb.services;

import org.springframework.stereotype.Service;
import xyz.karmishin.drontaxiweb.entities.Order;
import xyz.karmishin.drontaxiweb.repositories.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void imitateWork(Order order) {
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                order.setCurrentStatus(Order.Status.IN_PROGRESS);
                orderRepository.save(order);

                Thread.sleep(12000);
                order.setCurrentStatus(Order.Status.COMPLETE);
                orderRepository.save(order);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
