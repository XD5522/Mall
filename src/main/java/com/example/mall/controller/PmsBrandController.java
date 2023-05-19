package com.example.mall.controller;

import com.example.mall.common.api.CommonPage;
import com.example.mall.common.api.CommonResult;
import com.example.mall.mbg.model.PmsBrand;
import com.example.mall.service.PmsBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/brand")
public class PmsBrandController {
    @Autowired
    private PmsBrandService service;

    /**
     * 获取列表
     * @return 品牌列表
     */
    @GetMapping("listAll")
    public CommonResult<List<PmsBrand>> getBrandList(){
        return CommonResult.success(service.listAllBrand());
    }

    /**
     * 新建brand
     * @param pmsBrand
     * @return commonResult
     */
    @PostMapping("/create")
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand){
        CommonResult commonResult;
        int count = service.createBrand(pmsBrand);
        if(count==1){
            commonResult = CommonResult.success(pmsBrand);
            log.debug("添加brand成功",pmsBrand);
        }else{
            commonResult = CommonResult.failed("操作失败");
            log.debug("添加brand失败",pmsBrand);
        }
        return commonResult;
    }

    /**
     * 通过id对brand进行修改
     * @param id
     * @param pmsBrandDto
     * @param result
     * @return
     */
    @PostMapping("/update/{id}")
    public CommonResult updateBrand(@PathVariable("id") long id, @RequestBody PmsBrand pmsBrandDto, BindingResult result){
        CommonResult commonResult;
        int count = service.updateBrand(id,pmsBrandDto);
        if(count == 1){
            commonResult = CommonResult.success(pmsBrandDto);
            log.debug("通过id修改成功",commonResult);
        }else{
            commonResult = CommonResult.failed("修改失败");
            log.debug("通过id修改失败",commonResult);
        }
        return commonResult;
    }

    /**
     * 通过id删除brand
     * @param id
     * @return CommonResult
     */
    @GetMapping("/delete/{id}")
    public CommonResult deleteBrand(@PathVariable long id){
        int count = service.deleteBrand(id);
        if (count == 1){
            log.debug("删除成功,id=",id);
            return CommonResult.success(null);
        }else{
            log.debug("删除失败,id=",id);
            return CommonResult.failed("操作失败");
        }
    }

    /**
     * 通过id获取Brand
     * @param id
     * @return
     */
    @GetMapping("/GetBrand/{id}")
    public CommonResult<PmsBrand> GetBrand(@PathVariable long id){
        return CommonResult.success(service.getBrand(id));
    }

    /**
     * 分页获取list
     * @param pageNum 当前的页数
     * @param pageSize 页面大小
     * @return
     */
    @GetMapping("/list")
    public CommonResult<CommonPage<PmsBrand>> ListBrand(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                        @RequestParam(value="pageSize",defaultValue = "3")Integer pageSize){
        List<PmsBrand> brandList = service.listBrand(pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));
    }
}
