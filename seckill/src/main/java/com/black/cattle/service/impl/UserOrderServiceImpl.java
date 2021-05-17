package com.black.cattle.service.impl;

import com.black.cattle.entity.UserOrder;
import com.black.cattle.mapper.UserOrderMapper;
import com.black.cattle.service.UserOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author vicente
 * @since 2021-05-16
 */
@Service
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder> implements UserOrderService {

    @Autowired
    private UserOrderMapper mapper;

    @Override
    public void saveOrder(UserOrder order) {

        order.setOrderNo(String.valueOf(UUID.randomUUID()));

        order.setOrderFlag(0);

        order.setOrderTime(LocalDateTime.now());

        order.setOrderPrice(new BigDecimal(100));

        Integer result = mapper.saveOrder(order);

    }
}
