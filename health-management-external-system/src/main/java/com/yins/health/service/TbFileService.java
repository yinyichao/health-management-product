package com.yins.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yins.health.entity.TbFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件表;(TbFile)表服务接口
 *
 * @author yinyichao
 * @since 2025-05-27 19:10:53
 */
public interface TbFileService extends IService<TbFile> {
    String upload(MultipartFile file);
}

