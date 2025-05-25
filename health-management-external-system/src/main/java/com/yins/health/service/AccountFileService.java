package com.yins.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yins.health.dto.FolderCreateDto;
import com.yins.health.entity.AccountFile;

public interface AccountFileService extends IService<AccountFile> {
    /**
     * 创建文件夹
     *
     * @param req
     */
    void createFolder(FolderCreateDto req);
}
