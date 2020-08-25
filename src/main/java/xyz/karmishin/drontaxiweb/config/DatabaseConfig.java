package xyz.karmishin.drontaxiweb.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import xyz.karmishin.drontaxiweb.entities.Role;
import xyz.karmishin.drontaxiweb.entities.User;
import xyz.karmishin.drontaxiweb.repositories.RoleRepository;
import xyz.karmishin.drontaxiweb.repositories.UserRepository;

import java.time.LocalDateTime;

@Component
public class DatabaseConfig {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DatabaseConfig(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Создание ролей и аккаунта администратора при первом запуске.
    @EventListener
    public void appReady(ApplicationReadyEvent event) {

        // если в БД нет роли "пользователь", то это, скорее всего, первый запуск
        if (roleRepository.findBySystemName("ROLE_USER") == null) {

            // роли
            roleRepository.save(new Role("ROLE_USER", "Пользователь"));
            roleRepository.save(new Role("ROLE_ADMIN", "Администратор"));

            // аккаунт администратора
            User admin = new User(
                    "+79999999999",
                    passwordEncoder.encode("1234"),
                    "2000-01-01",
                    LocalDateTime.now()
            );
            admin.getRoles().add(roleRepository.findBySystemName("ROLE_USER"));
            admin.getRoles().add(roleRepository.findBySystemName("ROLE_ADMIN"));
            userRepository.save(admin);
        }
    }
}
