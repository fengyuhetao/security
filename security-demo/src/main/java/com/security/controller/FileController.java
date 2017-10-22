package com.security.controller;/**
 * Created by HT on 2017/10/09.
 */

import com.security.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * @author HT
 * @create 2017-10-09 20:21
 **/
@RestController
@RequestMapping("/file")
public class FileController {
    String folder = "D:\\java\\security\\security-demo\\src\\main\\java\\com\\security\\controller";

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        File localFile = new File(folder, new Date().getTime() + ".txt");
        file.transferTo(localFile);

        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void dowonload(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception{
//        将inputstream放到try后边的括号里，可以不用手动关闭流 inputsStream.close()
        try (InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));) {
            OutputStream outputStream = response.getOutputStream();
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=test.txt");

            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
