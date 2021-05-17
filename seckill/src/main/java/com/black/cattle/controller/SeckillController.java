package com.black.cattle.controller;

import com.black.cattle.base.ResultDto;
import com.black.cattle.service.impl.MQOrderServiceImpl;
import com.black.cattle.service.impl.MQStockServiceImpl;
import com.black.cattle.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private MQStockServiceImpl mqStockService;

    @PostMapping("/go")
    public ResultDto seckill(@RequestParam("username")String userName) {

        String goodsNo = "45207b28-124d-4e2f-b899-b30ee298ce60";

        userName = userName + UUID.randomUUID();

        return ResultUtils.ok(mqStockService.seckill(userName, goodsNo));
    }
}
