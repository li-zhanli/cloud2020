package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.CommonResult;
import com.atguigu.cloud.entities.Payment;
import com.atguigu.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/payment/create", method = RequestMethod.POST)
    public CommonResult create(Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入失败");
        if (result > 0) {
            return new CommonResult(200, "8001插入成功", result);
        } else {
            return new CommonResult(444, "8001插入失败", null);
        }
    }

    @RequestMapping(value = "/payment/get/{id}", method = RequestMethod.GET)
    public CommonResult getPaymentByID(@PathVariable("id") Long id) {
        Payment result = paymentService.getPaymentByID(id);
        log.info("查询结果" + result);
        if (result != null) {
            return new CommonResult(200, "8001查询成功", result);
        } else {
            return new CommonResult(444, "8001查询失败", null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();

        services.forEach((service) -> log.info("-------service---------"+service));

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances.forEach(instance -> {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        });

        return this.discoveryClient;
    }
}
