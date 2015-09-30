package au.com.totp.example.security;

import au.com.totp.example.security.model.SBTUser;
import au.com.totp.example.security.service.UserService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by pablocaif on 28/09/15.
 */
@Controller
@RequestMapping("/rest")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/user", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public @ResponseBody
    SBTUser createUser(@RequestBody SBTUser user) {
        SBTUser savedUser = userService.createUser(user);
        userService.createRoleForUser(savedUser);
        savedUser.setPassword("");
        return savedUser;
    }

    @RequestMapping(value = "/qrcode/{username}.png", method = RequestMethod.GET)
    public void generateQRCode(HttpServletResponse response, @PathVariable("username") String userName) throws WriterException, IOException {
        String otpProtocol = userService.generateOTPProtocol(userName);
        response.setContentType("image/png");
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = writer.encode(otpProtocol, BarcodeFormat.QR_CODE, 250, 250);
        MatrixToImageWriter.writeToStream(matrix, "PNG", response.getOutputStream());
        response.getOutputStream().flush();
    }

}
