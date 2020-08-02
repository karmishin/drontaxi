package xyz.karmishin.drontaxiweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.karmishin.drontaxiweb.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
