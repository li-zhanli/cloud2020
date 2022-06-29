package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.CommonResult;
import com.atguigu.cloud.entities.Payment;
import com.atguigu.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @RequestMapping(value = "/payment/create", method = RequestMethod.POST)
    public CommonResult create(Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入失败");
        if (result > 0){
            return  new CommonResult(200,"8002插入成功",result);
        }else {
            return  new CommonResult(444,"8002插入失败",null);
        }
    }

    @RequestMapping(value = "/payment/get/{id}", method = RequestMethod.GET)
    public CommonResult getPaymentByID(@PathVariable("id") Long id) {
        Payment result = paymentService.getPaymentByID(id);
        log.info("查询结果"+result);
        if (result != null){
            return  new CommonResult(200,"8002查询成功",result);
        }else {
            return  new CommonResult(444,"8002查询失败",null);
        }
    }
}
