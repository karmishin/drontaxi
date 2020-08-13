package xyz.karmishin.drontaxiweb.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String systemName, userFriendlyName;

    public Role() { }

    public Role(String systemName, String userFriendlyName) {
        this.systemName = systemName;
        this.userFriendlyName = userFriendlyName;
    }

    public Long getId() {
        return id;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getUserFriendlyName() {
        return userFriendlyName;
    }

    public void setUserFriendlyName(String userFriendlyName) {
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
