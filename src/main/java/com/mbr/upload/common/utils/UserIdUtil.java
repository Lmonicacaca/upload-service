package com.mbr.upload.common.utils;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class UserIdUtil {

    public static boolean validateUserId(HttpServletRequest request,String userId){

        String channel = request.getParameter("channel");
        if (!StringUtils.isEmpty(channel)&&channel.equals("zuul")) {
            String trueUserId = request.getParameter("trueUserId");
            if (trueUserId.equals(userId)){
                return true;
            }else{
                return false;
            }
        }else {
            return true;
        }

    }

}
