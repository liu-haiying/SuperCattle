package com.black.cattle.service.impl;

import com.black.cattle.entity.GoodsStock;
import com.black.cattle.mapper.GoodsStockMapper;
import com.black.cattle.service.GoodsStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author vicente
 * @since 2021-05-16
 */
@Service
public class GoodsStockServiceImpl extends ServiceImpl<GoodsStockMapper, GoodsStock> implements GoodsStockService {

    @Autowired
    private GoodsStockMapper mapper;

    @Override
    public void decrStock(String no) {
        mapper.updateById(no);
    }

    @Override
    public Integer selectStock(String no) {

        return mapper.selectStockNum(no);
    }
}
