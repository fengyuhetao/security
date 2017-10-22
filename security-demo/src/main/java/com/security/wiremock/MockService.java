package com.security.wiremock;/**
 * Created by HT on 2017/10/10.
 */

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * @author HT
 * @create 2017-10-10 16:40
 **/
public class MockService {

    public static void main(String [] args) throws IOException{
        configureFor(8081);
        removeAllMappings();

        mock("/order/1", "01");
    }

    private static void mock(String url, String filename) throws IOException {
        ClassPathResource resource = new ClassPathResource("mock/response/" + filename +".txt");
        String content = StringUtils.join(FileUtils.readLines(resource.getFile(), "UTF-8").toArray(), "\n");
        stubFor(get(urlPathEqualTo(url)).willReturn(aResponse().withBody(content).withStatus(200)));
    }
}
