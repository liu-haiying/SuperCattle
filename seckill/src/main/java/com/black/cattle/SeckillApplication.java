package com.black.cattle;

import com.black.cattle.service.GoodsStockService;
import com.black.cattle.utils.RedisUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.black.cattle.mapper")
public class SeckillApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SeckillApplication.class, args);
    }

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private GoodsStockService stockService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Integer stock = stockService.selectStock("45207b28-124d-4e2f-b899-b30ee298ce60");

        redisUtils.setValue("GOODS_STOCK_45207b28-124d-4e2f-b899-b30ee298ce60", stock, 20*60L);
    }
}
