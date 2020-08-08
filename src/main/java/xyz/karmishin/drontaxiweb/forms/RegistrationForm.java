package xyz.karmishin.drontaxiweb.forms;

import org.springframework.security.crypto.password.PasswordEncoder;
import xyz.karmishin.drontaxiweb.entities.User;

public class RegistrationForm {
    protected String phoneNumber, password, birthdate, passwordConfirm;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(phoneNumber, passwordEncoder.encode(password), birthdate);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}