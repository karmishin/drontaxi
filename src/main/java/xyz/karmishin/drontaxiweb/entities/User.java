package xyz.karmishin.drontaxiweb.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Set;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getPatronym() {
        return patronym;
    }

    public void setPatronym(String patronym) {
        this.patronym = patronym;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
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