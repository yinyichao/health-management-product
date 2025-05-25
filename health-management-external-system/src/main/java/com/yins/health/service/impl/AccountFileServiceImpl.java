package com.yins.health.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yins.health.constant.BizCodeEnum;
import com.yins.health.constant.FolderFlagEnum;
import com.yins.health.dao.AccountFileDao;
import com.yins.health.dto.AccountFileDto;
import com.yins.health.dto.FolderCreateDto;
import com.yins.health.entity.AccountFile;
import com.yins.health.exception.BizException;
import com.yins.health.service.AccountFileService;
import com.yins.health.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Ï
 *
 * @Description
 * @Author Darius
 * @Version 1.0
 **/
@Slf4j
@Service
public class AccountFileServiceImpl extends ServiceImpl<AccountFileDao, AccountFile> implements AccountFileService {

    /**
     * 创建文件夹
     *
     * @param req
     */
    @Override
    public void createFolder(FolderCreateDto req) {

        AccountFileDto accountFileDTO = AccountFileDto.builder().accountId(req.getAccountId())
                .parentId(req.getParentId())
                .fileName(req.getFolderName())
                .isDir(FolderFlagEnum.YES.getCode())
                .build();

        saveAccountFile(accountFileDTO);

    }
    /**
     * 处理用户和文件的关系，存储文件和文件夹都是可以的
     * <p>
     * 1、检查父文件是否存在
     * 2、检查文件是否重复
     * 3、保存相关文件关系
     *
     * @param accountFileDTO
     * @return
     */
    private Long saveAccountFile(AccountFileDto accountFileDTO) {
        //检查父文件是否存在
        checkParentFileId(accountFileDTO);

        AccountFile accountFileDO = CommonUtil.convert(accountFileDTO, AccountFile.class);

        //检查文件是否重复 aa  aa(1) aa(2)
        processFileNameDuplicate(accountFileDO);

        //保存相关文件关系
        baseMapper.insert(accountFileDO);

        return accountFileDO.getId();
    }
    /**
     * 检查父文件是否存在
     *
     * @param accountFileDTO
     */
    private void checkParentFileId(AccountFileDto accountFileDTO) {
        if (accountFileDTO.getParentId() != 0) {
            AccountFile accountFileDO = baseMapper.selectOne(
                    new QueryWrapper<AccountFile>()
                            .eq("id", accountFileDTO.getParentId())
                            .eq("account_id", accountFileDTO.getAccountId()));

            if (accountFileDO == null) {
                throw new BizException(BizCodeEnum.FILE_NOT_EXISTS);
            }
        }

    }
    /**
     * 处理文件是否重复,
     * 文件夹重复和文件名重复处理规则不一样
     *
     * @param accountFileDO
     */
    public Long processFileNameDuplicate(AccountFile accountFileDO) {

        Long selectCount = Long.valueOf(baseMapper.selectCount(new QueryWrapper<AccountFile>()
                .eq("account_id", accountFileDO.getAccountId())
                .eq("parent_id", accountFileDO.getParentId())
                .eq("is_dir", accountFileDO.getIsDir())
                .eq("file_name", accountFileDO.getFileName())));

        if (selectCount > 0) {
            //处理重复文件夹
            if (Objects.equals(accountFileDO.getIsDir(), FolderFlagEnum.YES.getCode())) {
                accountFileDO.setFileName(accountFileDO.getFileName() + "_" + System.currentTimeMillis());
            } else {
                //处理重复文件名,提取文件拓展名
                String[] split = accountFileDO.getFileName().split("\\.");
                accountFileDO.setFileName(split[0] + "_" + System.currentTimeMillis() + "." + split[1]);
            }
        }
        return selectCount;

    }
}