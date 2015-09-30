package au.com.totp.example.security.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by pablocaif on 16/09/15.
 */
@Entity
@Table(name = "users")
public class SBTUser {
    @Id
    private String username;

    private String password;
    private boolean enabled;
    private String secret;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<SBTUserRole> roles;

    public SBTUser() {
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Set<SBTUserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<SBTUserRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "CMUser{" +
                "username='" + username + '\'' +
                ", enabled=" + enabled +
                ", roles=" + roles +
                '}';
    }
}
