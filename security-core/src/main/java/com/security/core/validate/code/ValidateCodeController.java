package com.security.core.validate.code;/**
 * Created by HT on 2017/10/12.
 */

import com.security.core.validate.code.image.ImageCode;
import com.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author HT
 * @create 2017-10-12 15:23
 **/
@RestController
public class ValidateCodeController {
    protected static final String SESSION_IMAGE_KEY = "SESSION_KEY_IMAGE_CODE";

    protected static final String SESSION_SMS_KEY = "SESSION_KEY_SMS_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;

    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;

    @Autowired
    private SmsCodeSender smsCodeSender;

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        TODO 如果这里将ImageCode，改为ValidateCode，那么imageCode.getImage()将无法正常工作
        ImageCode imageCode = (ImageCode) imageCodeGenerator.generate(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_IMAGE_KEY, imageCode);
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }

    @GetMapping("/code/sms")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
        ValidateCode smsCode = smsCodeGenerator.generate(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_SMS_KEY, smsCode);
        String mobile = ServletRequestUtils.getStringParameter(request, "mobile");
        smsCodeSender.send(mobile, smsCode.getCode());
    }
}
