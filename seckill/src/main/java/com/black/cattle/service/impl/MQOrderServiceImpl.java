package com.black.cattle.service.impl;

import com.black.cattle.config.RabbitConfig;
import com.black.cattle.entity.UserOrder;
import com.black.cattle.service.UserOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MQOrderServiceImpl {

    @Autowired
    private UserOrderService orderService;

    @RabbitListener(queues = RabbitConfig.ORDER_QUEUE)
    public void saveOrder(UserOrder order) {

        log.info("收到订单消息，订单用户为：{}，商品名称为：{}", order.getOrderBy(), order.getOrderNo());

        orderService.saveOrder(order);
    }
}
