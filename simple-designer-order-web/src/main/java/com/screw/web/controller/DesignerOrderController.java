package com.screw.web.controller;

import com.screw.AppConfiguration;
import com.screw.DesignerOrderService;
import com.screw.DomainConfiguration;
import com.screw.domain.order.DesignerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/order", produces = "application/json;charset=utf-8")
@Import({DomainConfiguration.class, AppConfiguration.class})
public class DesignerOrderController {
    @Autowired
    private DesignerOrderService designerOrderService;

    @RequestMapping("/create")
    @ResponseBody
    public ResultMessage create(@RequestParam("customerId") @NotNull int customerId,
                                @NotNull int designerId) {
        DesignerOrder order = designerOrderService.createOrder(customerId, designerId);
        return ResultMessage.success(order);
    }
}

