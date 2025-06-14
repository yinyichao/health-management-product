package com.yins.health.controller;



import com.yins.health.service.TbFileService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import javax.annotation.Resource;
import com.yins.health.util.AppResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件表;(TbFile)表控制层
 *
 * @author yinyichao
 * @since 2025-05-27 19:10:53
 */
@Api(tags = "文件表;API")
@RestController
@RequestMapping("/api/file/v1")
public class TbFileController {
    /**
     * 服务对象
     */
    @Resource
    private TbFileService tbFileService;

    @PostMapping("/upload")
    @Operation(summary = "会话文件上传")
    public AppResult upload(@RequestBody MultipartFile file){
        return AppResult.successResult(tbFileService.upload(file));
    }
    @GetMapping("/mediaUrl")
    @Operation(summary = "会话文件上传")
    public AppResult mediaUrl(String mediaId){
        return AppResult.successResult(tbFileService.upload(mediaId));
    }
    @GetMapping("/weixinUrl")
    @Operation(summary = "会话文件上传")
    public AppResult weixinUrl(String mediaId){
        return AppResult.successResult(tbFileService.uploadWeixin(mediaId));
    }
/*    @GetMapping("/getDownloadUrl")
    @Operation(summary = "获取文件预览（临时）")
    public AppResult getDownloadUrl(String objectKey){
        return AppResult.successResult(tbFileService.getDownloadUrl(objectKey));
    }*/
}

