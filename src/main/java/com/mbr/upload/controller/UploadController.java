package com.mbr.upload.controller;


import com.mbr.upload.common.controller.BaseController;
import com.mbr.upload.common.utils.TimestampPkGenerator;
import com.mbr.upload.domain.Upload;
import com.mbr.upload.manager.UploadManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class UploadController extends BaseController<Upload> {
    @Value("${image_disk}")
    private String imageDisk;


    @Autowired
    private UploadManager uploadManager;

    @RequestMapping(value = "upload",method = RequestMethod.POST)
    @ResponseBody
    public Object upload(HttpServletRequest request){
        List<String> urlList = new ArrayList<>();
        CommonsMultipartResolver cmr = new CommonsMultipartResolver(request.getServletContext());
        if (cmr.isMultipart(request)) {
            MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) (request);
            Iterator<String> files = mRequest.getFileNames();
            while (files.hasNext()) {
                MultipartFile file = mRequest.getFile(files.next());

                if (file != null) {
                    Upload upload = new Upload();
                    upload.setId(new TimestampPkGenerator().next(getClass()));
                    upload.setCreateTime(new Date());

                    String fileName = file.getOriginalFilename();
                    int lastIndex = fileName.lastIndexOf(".");
                    String type = fileName.substring(lastIndex + 1, fileName.length());


                    fileName = UUID.randomUUID().toString().replace("-", "") + "." + type;
                    upload.setContentType(type);
                    upload.setSize(fileName.length() + "");
                    upload.setFileName(fileName);
                    String newImageDisk = imageDisk;

                    newImageDisk += fileName;
                    upload.setDiskUrl(newImageDisk);
                    upload.setInternetUrl("/download/" + upload.getId());
                    try {
                        File f = new File(newImageDisk);
                        File parentFile = f.getParentFile();
                        if (!parentFile.isDirectory()) {
                            f.mkdirs();
                        }
                        file.transferTo(new File(newImageDisk));
                        uploadManager.save(upload);
                        urlList.add(upload.getInternetUrl());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return success(urlList);

    }

}