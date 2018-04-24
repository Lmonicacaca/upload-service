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
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class UploadController extends BaseController<Upload> {
    @Value("${image_disk}")
    private String imageDisk;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");

    @Autowired
    private UploadManager uploadManager;

    @RequestMapping(value = "upload",method = RequestMethod.POST)
    @ResponseBody
    public Object upload(HttpServletRequest request){
        Map<String,String> map = new HashMap<>();
        CommonsMultipartResolver cmr = new CommonsMultipartResolver(request.getServletContext());
        if (cmr.isMultipart(request)) {
            MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) (request);
            Map<String, MultipartFile> mapFiles = mRequest.getFileMap();
            for(Map.Entry<String, MultipartFile> entry : mapFiles.entrySet()) {
                MultipartFile file = entry.getValue();
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
                    String date = simpleDateFormat.format(new Date());
                    newImageDisk = newImageDisk+"/"+date;//目录
                    File f = new File(newImageDisk);
                    if(!f.isDirectory()){
                        f.mkdirs();
                    }
                    upload.setDiskUrl(newImageDisk+"/"+fileName);
                    upload.setInternetUrl("/download/" + upload.getId());
                    try {
                        file.transferTo(new File(newImageDisk));
                        uploadManager.save(upload);
                        map.put(entry.getKey(),upload.getInternetUrl());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return success(map);

    }

}
