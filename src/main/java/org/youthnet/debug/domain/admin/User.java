package org.youthnet.debug.domain.admin;

import javax.persistence.*;

/**
 * User: karl
 * Date: 18-May-2010
 */
@Entity
@Table(name = "app_user")
public class User extends BaseObject {

    @Column(nullable=false,length=50,unique=true)
    private String username;

    @Column(nullable=false)
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name="password_hint")
    private String passwordHint;

    @Column(name="first_name",nullable=false,length=50)
    private String firstName;

    @Column(name="last_name",nullable=false,length=50)
    private String lastName;

    @Column(nullable=false,unique=true)
    private String email;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column
    private String website;

    @Version
    private Integer version;

//    private Set<Role> roles = new HashSet<Role>();

    @Column(name="account_enabled")
    private boolean enabled;

    @Column(name="account_expired",nullable=false)
    private boolean accountExpired;

    @Column(name="account_locked",nullable=false)
    private boolean accountLocked;

    @Column(name="credentials_expired",nullable=false)
    private boolean credentialsExpired;

//    private Vuo vuo;


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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPasswordHint() {
        return passwordHint;
    }

    public void setPasswordHint(String passwordHint) {
        this.passwordHint = passwordHint;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }
}