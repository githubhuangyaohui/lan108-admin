package com.persistenthuang.lan108admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.persistenthuang.lan108admin.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Controller
public class ImageController {

    //时间格式化
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");

    //图片上传路径
    @Value("${file-save-path}")
    private String fileSavePath;

    @CrossOrigin
    @PostMapping("/api/covers")
    @ResponseBody
    public String coversUpload(MultipartFile file, HttpServletRequest request) {
        log.info("开始上传图片");
        //1.后半段目录：  2020/03/15
        String directory = simpleDateFormat.format(new Date());
        /**
         *  2.文件保存目录  E:/images/2020/03/15/
         *  如果目录不存在，则创建
         */
        File dir = new File(fileSavePath + directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        log.info("图片上传，保存位置：" + fileSavePath + directory);

        //拼接生成文件名
        String fileName = StringUtils.getUUID32();
        String oldName = file.getOriginalFilename();
        log.info("上传文件的原始名称为：" + oldName);
        if (oldName == null) {
            return null;
        }
        fileName = fileName + oldName.substring(oldName.lastIndexOf("."));
        //创建上传文件
        File f = new File(fileSavePath + directory + fileName);
        //上传文件
        try {
            file.transferTo(f);
            //返回的图片url
            String imgURL = request.getScheme()
                    + "://" + request.getServerName()
                    + ":" + request.getServerPort()
                    + "/images/"
                    + directory + fileName;

            log.info("上传成功" + imgURL);
            return JSONObject.toJSONString(imgURL);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("上传失败");
            return null;
        }
    }
}
