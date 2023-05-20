package com.example.mall.service.Impl;

import com.example.mall.mbg.mapper.PmsBrandMapper;
import com.example.mall.mbg.model.PmsBrand;
import com.example.mall.mbg.model.PmsBrandExample;
import com.example.mall.service.PmsBrandService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PmsBrandServiceImpl implements PmsBrandService {
    @Autowired
    PmsBrandMapper mapper;

    @Override
    public List<PmsBrand> listAllBrand() {
        return mapper.selectByExample(new PmsBrandExample());
    }

    @Override
    public int createBrand(PmsBrand brand) {
        return mapper.insertSelective(brand);
    }

    @Override
    public int updateBrand(Long id, PmsBrand brand) {
        brand.setId(id);
        return mapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public int deleteBrand(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<PmsBrand> listBrand(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return mapper.selectByExample(new PmsBrandExample());
    }

    @Override
    public PmsBrand getBrand(Long id) {
        return mapper.selectByPrimaryKey(id);
    }
}
