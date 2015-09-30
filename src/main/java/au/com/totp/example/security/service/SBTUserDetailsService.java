package au.com.totp.example.security.service;

import au.com.totp.example.security.model.SBTUser;
import au.com.totp.example.security.respository.UserRepository;
import au.com.totp.example.security.totp.userdetails.TOTPUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by pablocaif on 16/09/15.
 */
@Component
public class SBTUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SBTUser user = userRepository.findOne(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        UserDetails userDetails = new TOTPUserDetails(user);
        return userDetails;

    }
}
