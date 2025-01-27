package com.yummy.controller.admin;

import com.yummy.constant.MessageConstant;
import com.yummy.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@Slf4j
public class CommonController {

    @PostMapping("/upload")
    public Result<String> uploadFile(MultipartFile file){
        try{
            log.info("upload a file:{}", file);
            // get original file name
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            // generate new file name using uuid
            String newFileName = UUID.randomUUID().toString() + suffix;
            // save the file to local directory
            file.transferTo( new File("/Users/wzc/Downloads/"+ newFileName));
            return Result.success("/Users/wzc/Downloads/"+ newFileName);
        }catch (IOException e){
            log.error("upload file failed");
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);

    }
}
