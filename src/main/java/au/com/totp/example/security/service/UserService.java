package au.com.totp.example.security.service;

import au.com.totp.example.security.model.SBTUser;
import au.com.totp.example.security.model.SBTUserRole;
import au.com.totp.example.security.respository.RoleRepository;
import au.com.totp.example.security.respository.UserRepository;
import org.apache.commons.codec.binary.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

/**
 * Created by pablocaif on 28/09/15.
 */
@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Bean
    private PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    public SBTUser createUser(SBTUser user) {
        user.setPassword(encoder().encode(user.getPassword()));
        user.setSecret(generateSecret());
        return userRepository.save(user);
    }

    private String generateSecret() {
        byte [] buffer = new byte[10];
        new SecureRandom().nextBytes(buffer);
        return new String(new Base32().encode(buffer));
    }

    public SBTUserRole createRoleForUser(SBTUser user) {
        SBTUserRole userRole = new SBTUserRole();
        userRole.setRole("USER");
        userRole.setUser(user);
        return roleRepository.save(userRole);
    }

    public String generateOTPProtocol(String userName) {
        SBTUser user = userRepository.findOne(userName);
        return String.format("otpauth://totp/%s:%s?secret=%s&issuer=SpringBootTOTP", userName, userName + "@domain.com", user.getSecret());
    }
}
