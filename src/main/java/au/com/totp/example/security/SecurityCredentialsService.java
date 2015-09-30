package au.com.totp.example.security;

import au.com.totp.example.security.totp.userdetails.TOTPUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Created by pablocaif on 22/09/15.
 */
@Service
public class SecurityCredentialsService {
    public UserDetails findLoggedUserDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof TOTPUserDetails) {
            return (UserDetails) principal;
        } else {
            throw new IllegalStateException("Error obtaining credentials for logged in user. " +
                    "The credentials are not an instance of UserDetails. Something is seriously wrong");
        }
    }
}
