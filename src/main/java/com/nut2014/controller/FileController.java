package com.nut2014.controller;

import com.nut2014.config.ConfigResource;
import com.nut2014.entity.MyLog;
import com.nut2014.exception.UploadFileException;
import com.nut2014.pojo.BaseResponse;

import com.nut2014.utils.FileNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件上传
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    ConfigResource configResource;

    /**
     * 上传图片
     * @param file 文件
     * @return
     */
    @PostMapping("/upload")
    public BaseResponse<String> upload(@RequestParam("file") MultipartFile file) throws UploadFileException {
        //图片按天分目录
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String datePathStr = sdf.format(new Date()) + "/";
        //获取配置的目录
        String path = configResource.getFileUploadPath() + datePathStr;
        //获取文件名
        String fileName = FileNameUtils.getFileName(file.getOriginalFilename());
        File newFile = new File(path + fileName);
        //父目录不存在 自动创建
        if (!newFile.getParentFile().exists()) {
            boolean mkdir = newFile.getParentFile().mkdir();
            if (!mkdir) {
                return new BaseResponse<>(0, "文件读写错误", "");
            }
        }
        try {
            file.transferTo(newFile);
            return new BaseResponse<>(1, "上传成功", configResource.getServerHost() + configResource.getFileLocation() + datePathStr + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new BaseResponse<>(0, "上传失败", e.getMessage());
        }
    }


}
