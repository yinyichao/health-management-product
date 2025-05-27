package com.yins.health.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @Description
 * @Author Darius
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FolderCreateDto {

    /**
     * 文件夹名称
     */
    private String folderName;

    /**
     * 上级文件夹ID
     */
    private Long parentId;

    /**
     * 用户ID
     */
    private Integer accountId;
}