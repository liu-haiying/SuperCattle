package com.black.cattle.service.impl;

import com.black.cattle.config.RabbitConfig;
import com.black.cattle.entity.UserOrder;
import com.black.cattle.service.GoodsStockService;
import com.black.cattle.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MQStockServiceImpl {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private GoodsStockService stockService;

    @Autowired
    private RedisUtils redisUtils;

    @RabbitListener(queues = RabbitConfig.STOCK_QUEUE)
    public void decrStock(String no) {

        log.info("库存消息队列收到的消息商品信息是：{}", no);

        stockService.decrStock(no);
    }

    public String seckill(String username, String goodsNo) {

        log.info("参加秒杀的用户是：{}，秒杀的商品是：{}", username, goodsNo);

        String message = "";

        Long decrByResult = redisUtils.decrNum("GOODS_STOCK_" + goodsNo);

        log.info("现在redis中的数据{}", decrByResult);

        if (decrByResult >= 0) {
            /**
             * 说明该商品的库存量有剩余，可以进行下订单操作
             */
            log.info("用户：{}秒杀该商品：{}库存有余，可以进行下订单操作", username, goodsNo);
            //发消息给库存消息队列，将库存数据减一
            rabbitTemplate.convertAndSend(RabbitConfig.STOCK_EXCHANGE, RabbitConfig.STOCK_ROUTING_KEY, goodsNo);
            //发消息给订单消息队列，创建订单
            UserOrder order = new UserOrder();

            order.setOrderBy(username);;

            rabbitTemplate.convertAndSend(RabbitConfig.ORDER_EXCHANGE, RabbitConfig.ORDER_ROUTING_KEY, order);

            message = "用户" + username + "秒杀" + goodsNo + "成功";

        } else {
            /**
             * 说明该商品的库存量没有剩余，直接返回秒杀失败的消息给用户
             */
            log.info("用户：{}秒杀时商品的库存量没有剩余,秒杀结束", username);

            message = "用户："+ username + "商品的库存量没有剩余,秒杀结束";
        }

        return message;
    }
}
