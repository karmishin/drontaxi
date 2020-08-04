package xyz.karmishin.drontaxiweb.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Set;

@Data
@Entity
@Table(name="t_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String username, password;
    @Transient
    private String passwordConfirm;
    @Email
    @NotEmpty
    private String emailAddress;
    @NotEmpty
    private String firstName, lastName, patronym, birthdate;
    @Pattern(message="Неправильный формат номера телефона", regexp = "^[+]7\\d{10}")
    private String phoneNumber;
    private enum gender { MALE, FEMALE };
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    public User() { }

    public User(@NotEmpty String username, @NotEmpty String password, String passwordConfirm, @Email @NotEmpty String emailAddress, @NotEmpty String firstName, @NotEmpty String lastName, @NotEmpty String patronym, @NotEmpty String phoneNumber, @NotEmpty String birthdate) {
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronym = patronym;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
    }

    public User(@NotEmpty String username, @NotEmpty String password, String passwordConfirm, @Email @NotEmpty String emailAddress, @NotEmpty String firstName, @NotEmpty String lastName, @NotEmpty String patronym, @NotEmpty String phoneNumber, @NotEmpty String birthdate, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronym = patronym;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
        this.roles = roles;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }
}