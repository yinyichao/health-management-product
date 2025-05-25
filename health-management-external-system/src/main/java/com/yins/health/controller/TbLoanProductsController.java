package com.yins.health.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yins.health.entity.TbLoanProducts;
import com.yins.health.service.TbLoanProductsService;
import com.yins.health.util.AppResult;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 贷款产品表(TbLoanProducts)表控制层
 *
 * @author yinyichao
 * @since 2025-05-13 11:31:16
 */
@Api(tags = "贷款产品表API")
@RestController
@RequestMapping("/api/tbLoanProducts/v1")
public class TbLoanProductsController {
    /**
     * 服务对象
     */
    @Resource
    private TbLoanProductsService tbLoanProductsService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param tbLoanProducts 查询实体
     * @return 所有数据
     */
    @GetMapping("/selectAll")
    public AppResult selectAll(Page<TbLoanProducts> page, TbLoanProducts tbLoanProducts) {
        return AppResult.successResult(this.tbLoanProductsService.page(page, new QueryWrapper<>(tbLoanProducts)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne/{id}")
    public AppResult selectOne(@PathVariable Serializable id) {
        return AppResult.successResult(this.tbLoanProductsService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param tbLoanProducts 实体对象
     * @return 新增结果
     */
    @PostMapping("/insert")
    public AppResult insert(@RequestBody TbLoanProducts tbLoanProducts) {
        return AppResult.successResult(this.tbLoanProductsService.save(tbLoanProducts));
    }

    /**
     * 修改数据
     *
     * @param tbLoanProducts 实体对象
     * @return 修改结果
     */
    @PostMapping("/update")
    public AppResult update(@RequestBody TbLoanProducts tbLoanProducts) {
        return AppResult.successResult(this.tbLoanProductsService.updateById(tbLoanProducts));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @PostMapping("/delete")
    public AppResult delete(@RequestParam("idList") List<Long> idList) {
        return AppResult.successResult(this.tbLoanProductsService.removeByIds(idList));
    }
}

