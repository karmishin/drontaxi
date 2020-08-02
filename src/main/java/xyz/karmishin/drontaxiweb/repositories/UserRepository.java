package xyz.karmishin.drontaxiweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.karmishin.drontaxiweb.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
