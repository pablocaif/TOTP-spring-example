package au.com.totp.example.security.totp.authdetails;

import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by pablocaif on 16/09/15.
 */
public class TOTPWebAuthenticationDetails extends WebAuthenticationDetails {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private Integer totpKey;

    /**
     * Records the remote address and will also set the session Id if a session
     * already exists (it won't create one).
     *
     * @param request that the authentication request was received from
     */
    public TOTPWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        String totpKeyString = request.getParameter("TOTPKey");
        if (StringUtils.hasText(totpKeyString)) {
            try {
                this.totpKey = Integer.valueOf(totpKeyString);
            }
            catch (NumberFormatException e) {
                this.totpKey = null;
            }
        }
    }

    public Integer getTotpKey() {
        return this.totpKey;
    }
}
