package com.security.dto;/**
 * Created by HT on 2017/10/09.
 */

import lombok.Data;

/**
 * @author HT
 * @create 2017-10-09 20:23
 **/
@Data
public class FileInfo {
    private String path;

    public FileInfo(String path) {
        this.path = path;
    }
}
