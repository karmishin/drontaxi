package xyz.karmishin.drontaxiweb.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import java.util.Set;

@Data
@Entity
public class Role implements GrantedAuthority {
    @Id
    private Long id;

    private String systemName, userFriendlyName;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {}

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String systemName, String userFriendlyName) {
        this.id = id;
        this.systemName = systemName;
        this.userFriendlyName = userFriendlyName;
    }

    @Override
    public String getAuthority() {
        return getSystemName();
    }

    @Override
    public String toString() {
        return getUserFriendlyName();
    }
}
