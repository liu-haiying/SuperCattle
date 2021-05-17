package com.black.cattle.service;

import com.black.cattle.entity.UserOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author vicente
 * @since 2021-05-16
 */
public interface UserOrderService extends IService<UserOrder> {

    /**
     * 下单
     * @param order 订单实体
     */
    void saveOrder(UserOrder order);
}
