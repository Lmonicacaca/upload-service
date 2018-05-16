package com.mbr.upload.controller;

import com.mbr.upload.common.utils.CommonUtils;
import com.mbr.upload.domain.Upload;
import com.mbr.upload.manager.UploadManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;

@Controller
public class DownloadController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${image_disk}")
    private String imageDisk;

    @Autowired
    private UploadManager uploadManager;

    @RequestMapping(value = "download/{file}")
    public ResponseEntity<byte[]> download(@PathVariable("file") Long file, HttpServletRequest request)throws Exception{

        Upload upload = uploadManager.queryById(file);
        File f = new File(upload.getDiskUrl());
        String fileName = f.getName();
        int len = fileName.lastIndexOf(".");
        String imagesPattern  = fileName.substring(len+1,fileName.length()).toLowerCase();
        ResponseEntity<byte[]> responseEntity ;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Length",f.length()+"");
        headers.add("Cache-Control", f.length()+"");
        if (CommonUtils.isImage(f)) {
            if (imagesPattern.equals("png")) {
                headers.setContentType(MediaType.IMAGE_PNG);
            }else if (imagesPattern.equals("jpg")||imagesPattern.equals("jpeg")){
                headers.setContentType(MediaType.IMAGE_JPEG);
            }else if (imagesPattern.equals("gif")){
                headers.setContentType(MediaType.IMAGE_GIF);
            }
            headers.add("Accept-Ranges", "bytes");
            headers.add("Pragma", "no-cache");

        }else if(imagesPattern.equals("apk")){
            headers.setContentType(MediaType.valueOf("application/vnd.android.package-archive"));

            headers.setContentDispositionFormData("attachment",new String(f.getName().getBytes("gbk"), "ISO8859-1"));
        }else{
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment",new String(f.getName().getBytes("gbk"), "ISO8859-1"));
        }
        HttpStatus statusCode;
        if(f.exists()){
            if (imagesPattern.equals("apk")){
                statusCode = HttpStatus.OK;
                responseEntity = new ResponseEntity(FileUtils.readFileToByteArray(f), headers, statusCode);
                return responseEntity;

            }else {
                statusCode = HttpStatus.OK;
                responseEntity = new ResponseEntity(FileUtils.readFileToByteArray(f), headers, statusCode);
                return responseEntity;
            }

        }else {
            statusCode = HttpStatus.NOT_FOUND;
            responseEntity = new ResponseEntity( headers, statusCode);
            return responseEntity;
        }

    }

}
