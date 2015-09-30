package au.com.totp.example.security.totp;

import au.com.totp.example.security.service.SBTUserDetailsService;
import au.com.totp.example.security.totp.authdetails.TOTPWebAuthenticationDetailsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by pablocaif on 16/09/15.
 */
@Component
public class TOTPConfigurator {
    @Autowired
    private SBTUserDetailsService cmUserDetailsService;

    public void configureAuthenticationDetailsSource(HttpSecurity http) throws Exception{
        http.formLogin().authenticationDetailsSource(new TOTPWebAuthenticationDetailsSource());
    }

    public void configureAuthenticationProvider(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        TOTPAuthenticationProvider authenticationProvider = new TOTPAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(encoder);
        authenticationProvider.setUserDetailsService(cmUserDetailsService);
        authenticationProvider.setTotpAuthenticator(new TOTPAuthenticator());

        auth.authenticationProvider(authenticationProvider);
    }
}
