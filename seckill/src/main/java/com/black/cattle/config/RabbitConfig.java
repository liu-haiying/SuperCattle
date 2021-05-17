package com.black.cattle.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.management.MXBean;

@Configuration
public class RabbitConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private Integer port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    // 库存交换机
    public static final String STOCK_EXCHANGE = "STOCK_EXCHANGE";

    // 订单交换机
    public static final String ORDER_EXCHANGE = "ORDER_EXCHANGE";

    // 库存队列
    public static final String STOCK_QUEUE = "STOCK_QUEUE";

    // 订单队列
    public static final String ORDER_QUEUE = "ORDER_QUEUE";

    // 库存路由键
    public static final String STOCK_ROUTING_KEY = "STOCK_ROUTING_KEY";

    // 订单路由键
    public static final String ORDER_ROUTING_KEY = "ORDER_ROUTING_KEY";

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {

        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public ConnectionFactory connectionFactory() {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);

        connectionFactory.setUsername(username);

        connectionFactory.setPassword(password);

        connectionFactory.setVirtualHost("/");

        return connectionFactory;
    }

    /**
     * 创建库存交换机
     * @return
     */
    @Bean
    public Exchange stockExchange() {
        return ExchangeBuilder.directExchange(STOCK_EXCHANGE).durable(true).build();
    }

    /**
     *
     * 创建库存队列
     * @return
     */
    @Bean
    public Queue stockQueue() {
        return new Queue(STOCK_QUEUE, true);
    }

    /**
     * 绑定库存交换机与库存队列
     * @return
     */
    @Bean
    public Binding stockBinding() {
        return BindingBuilder.bind(stockQueue()).to(stockExchange()).with(STOCK_ROUTING_KEY).noargs();
    }

    /**
     * 创建订单交换机
     * @return
     */
    @Bean
    public Exchange orderExchange() {
        return ExchangeBuilder.directExchange(ORDER_EXCHANGE).durable(true).build();
    }

    /**
     *
     * 创建库存队列
     * @return
     */
    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_QUEUE, true);
    }

    /**
     * 绑定订单交换机和订单队列
     * @return
     */
    @Bean
    public Binding orderBinding() {
        return BindingBuilder.bind(orderQueue()).to(orderExchange()).with(ORDER_ROUTING_KEY).noargs();
    }
}
