package xyz.karmishin.drontaxiweb.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import xyz.karmishin.drontaxiweb.entities.Role;
import xyz.karmishin.drontaxiweb.repositories.RoleRepository;

@Component
public class DatabaseConfig {
    private final RoleRepository roleRepository;

    public DatabaseConfig(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        if (roleRepository.findBySystemName("ROLE_USER") == null) {
            roleRepository.save(new Role("ROLE_USER", "Пользователь"));
            roleRepository.save(new Role("ROLE_ADMIN", "Администратор"));
        }
    }
}
