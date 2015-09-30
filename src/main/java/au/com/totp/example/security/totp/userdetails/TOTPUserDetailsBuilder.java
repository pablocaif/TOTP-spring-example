package au.com.totp.example.security.totp.userdetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by pablocaif on 16/09/15.
 */
public class TOTPUserDetailsBuilder {

    public static final String DEFAULT_USERNAME = "user";
    public static final String DEFAULT_PASSWORD = "password";
    public static final boolean DEFAULT_ENABLED = true;
    public static final String DEFAULT_SECRET = "secret";
    @SuppressWarnings("unchecked")
    public static final Collection<GrantedAuthority> DEFAULT_AUTHORITIES = (Collection) Arrays.asList(
            new SimpleGrantedAuthority("ROLE")
    );

    private String username = DEFAULT_USERNAME;
    private String password = DEFAULT_PASSWORD;
    private boolean enabled = DEFAULT_ENABLED;
    private String secret = DEFAULT_SECRET;
    private Collection<GrantedAuthority> authorities = DEFAULT_AUTHORITIES;

    public static TOTPUserDetailsBuilder aTOTPUserDetails() {
        return new TOTPUserDetailsBuilder();
    }

    public TOTPUserDetails build(){
        TOTPUserDetails userDetails = new TOTPUserDetails(username, password, enabled, secret);
        userDetails.setAuthorities(authorities);
        return userDetails;
    }

    public TOTPUserDetailsBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public TOTPUserDetailsBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public TOTPUserDetailsBuilder withEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public TOTPUserDetailsBuilder withSecret(String secret) {
        this.secret = secret;
        return this;
    }

    public TOTPUserDetailsBuilder withAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
        return this;
    }
}
