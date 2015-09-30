package au.com.totp.example.security.totp;

import au.com.totp.example.security.totp.authdetails.TOTPWebAuthenticationDetails;
import au.com.totp.example.security.totp.exception.MissingTOTPKeyAuthenticatorException;
import au.com.totp.example.security.totp.userdetails.TOTPUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by pablocaif on 16/09/15.
 */
public class TOTPAuthenticationProvider extends DaoAuthenticationProvider {
    private final Logger logger = LoggerFactory.getLogger(TOTPAuthenticationProvider.class);
    private TOTPAuthenticator totpAuthenticator;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {

        super.additionalAuthenticationChecks(userDetails, authentication);

        if (authentication.getDetails() instanceof TOTPWebAuthenticationDetails) {
            String secret = ((TOTPUserDetails) userDetails).getSecret();

            if (StringUtils.hasText(secret)) {
                Integer totpKey = ((TOTPWebAuthenticationDetails) authentication
                        .getDetails()).getTotpKey();
                if (totpKey != null) {
                    try {
                        if (!totpAuthenticator.verifyCode(secret, totpKey, 2)) {
                            logger.info("Code {} was not valid", totpKey);
                            System.out.printf("Code %d was not valid", totpKey);
                            throw new BadCredentialsException(
                                    "Invalid TOTP code");
                        }
                    }
                    catch (InvalidKeyException | NoSuchAlgorithmException e) {
                        throw new InternalAuthenticationServiceException(
                                "TOTP code verification failed", e);
                    }

                }
                else {
                    throw new MissingTOTPKeyAuthenticatorException(
                            "TOTP code is mandatory");
                }

            }
        }

    }

    public TOTPAuthenticator getTotpAuthenticator() {
        return totpAuthenticator;
    }

    public void setTotpAuthenticator(TOTPAuthenticator totpAuthenticator) {
        this.totpAuthenticator = totpAuthenticator;
    }
}
