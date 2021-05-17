package com.black.cattle.service;

import com.black.cattle.entity.GoodsStock;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author vicente
 * @since 2021-05-16
 */
public interface GoodsStockService extends IService<GoodsStock> {

    /**
     *
     * 秒杀后库存减1
     * @param no
     */
    void decrStock(String no);

    /**
     *
     * 查询是否有库存
     * @param no
     * @return
     */
    Integer selectStock(String no);
}
