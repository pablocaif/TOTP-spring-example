package au.com.totp.example.security.model;

import javax.persistence.*;

/**
 * Created by pablocaif on 16/09/15.
 */
@Entity
@Table(name = "authorities")
public class SBTUserRole {
    @Id
    @Column(name = "authority")
    private String role;

    @ManyToOne
    @JoinColumn(name = "username")
    private SBTUser user;

    public SBTUserRole() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public SBTUser getUser() {
        return user;
    }

    public void setUser(SBTUser user) {
        this.user = user;
    }
}
