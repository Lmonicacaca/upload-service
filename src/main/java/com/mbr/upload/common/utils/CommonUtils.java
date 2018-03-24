package com.mbr.upload.common.utils;

import java.io.File;

public class CommonUtils {

    public static boolean isImage(File file){
        if(file.isDirectory()){
            return false;
        }
        String fileName = file.getName();
        int len = fileName.indexOf(".");
        String imagesPattern  = fileName.substring(len+1,fileName.length()).toLowerCase();
        if("jpg".equals(imagesPattern)){
            return true;
        }else if("bmp".equals(imagesPattern)){
            return true;
        }else if("gif".equals(imagesPattern)){
            return true;
        }else if("psd".equals(imagesPattern)){
            return true;
        }else if("pcx".equals(imagesPattern)){
            return true;
        }else if("png".equals(imagesPattern)){
            return true;
        }else if("dxf".equals(imagesPattern)){
            return true;
        }else if("cdr".equals(imagesPattern)){
            return true;
        }else if("ico".equals(imagesPattern)){
            return true;
        }else if("bmp".equals(imagesPattern)){
            return true;
        }else if("jpeg".equals(imagesPattern)){
            return true;
        }else if("svg".equals(imagesPattern)){
            return true;
        }else if("wmf".equals(imagesPattern)){
            return true;
        }else if("emf".equals(imagesPattern)){
            return true;
        }else if("eps".equals(imagesPattern)){
            return true;
        }else if("tga".equals(imagesPattern)){
            return true;
        }else if("nef".equals(imagesPattern)){
            return true;
        }else if("tif".equals(imagesPattern)){
            return true;
        }else if("tiff".equals(imagesPattern)){
            return true;
        }else{
            return false;
        }

    }
}
