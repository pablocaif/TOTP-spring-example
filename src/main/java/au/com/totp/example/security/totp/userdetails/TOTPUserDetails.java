package au.com.totp.example.security.totp.userdetails;

import au.com.totp.example.security.model.SBTUser;
import au.com.totp.example.security.model.SBTUserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pablocaif on 16/09/15.
 */
public class TOTPUserDetails implements UserDetails {
    private String username;
    private String password;
    private boolean enabled;
    private String secret;
    private Collection<GrantedAuthority> authorities = new HashSet<>();

    public TOTPUserDetails(String username, String password, boolean enabled, String secret) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.secret = secret;
    }

    public TOTPUserDetails(SBTUser user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.enabled = user.isEnabled();
        this.secret = user.getSecret();
        populateAuthorities(user.getRoles());
    }

    private void populateAuthorities(Set<SBTUserRole> roles) {
        for (SBTUserRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return enabled;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getSecret() {
        return secret;
    }
}
