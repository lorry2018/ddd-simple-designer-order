package com.screw.web.controller;

import com.screw.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/customer", produces = "application/json;charset=utf-8")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/canCreateDesignerOrder")
    @ResponseBody
    public ResultMessage canCreateDesignerOrder(@RequestParam("customerId") @NotNull int customerId) {
        boolean can = customerService.canCreateDesignerOrder(customerId);
        return ResultMessage.success(can);
    }

    @RequestMapping("/enable")
    @ResponseBody
    public ResultMessage enable(@RequestParam("customerId") @NotNull int customerId) {
        customerService.enable(customerId);
        return ResultMessage.success();
    }

    @RequestMapping("/disable")
    @ResponseBody
    public ResultMessage disable(@RequestParam("customerId") @NotNull int customerId) {
        customerService.disable(customerId);
        return ResultMessage.success();
    }
}
