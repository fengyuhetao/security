package com.security.core.validate.code.image;/**
 * Created by HT on 2017/10/12.
 */

import com.security.core.validate.code.ValidateCode;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author HT
 * @create 2017-10-12 15:19
 **/
@Data
public class ImageCode extends ValidateCode {
    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, Integer expireIn) {
        super(code, expireIn);
        this.image = image;
    }
}
