package com.screw.web.controller;

import com.screw.DesignerOrderService;
import com.screw.domain.order.DesignerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/order", produces = "application/json;charset=utf-8")
public class DesignerOrderController {
    @Autowired
    private DesignerOrderService designerOrderService;

    @RequestMapping("/selectByKey")
    @ResponseBody
    public ResultMessage create(@RequestParam("orderId") @NotNull int orderId) {
        return ResultMessage.success(designerOrderService.selectByKey(orderId));
    }

    @RequestMapping("/create")
    @ResponseBody
    public ResultMessage create(@RequestParam("customerId") @NotNull int customerId,
                                @NotNull int designerId) {
        DesignerOrder order = designerOrderService.createOrder(customerId, designerId);
        return ResultMessage.success(order);
    }

    @RequestMapping("/quote")
    @ResponseBody
    public ResultMessage quote(@RequestParam("orderId") @NotNull int orderId,
                      @RequestParam("expectedAmount") @NotNull float expectedAmount,
                      @RequestParam("estimatedDays") @NotNull int estimatedDays) {
        designerOrderService.quote(orderId, expectedAmount, estimatedDays);
        return ResultMessage.success();
    }

    @RequestMapping("/acceptPrice")
    @ResponseBody
    public ResultMessage acceptPrice(@RequestParam("orderId") @NotNull int orderId) {
        designerOrderService.acceptPrice(orderId);
        return ResultMessage.success();
    }

    @RequestMapping("/rejectPrice")
    @ResponseBody
    public ResultMessage rejectPrice(@RequestParam("orderId") @NotNull int orderId) {
        designerOrderService.rejectPrice(orderId);
        return ResultMessage.success();
    }

    @RequestMapping("/pay")
    @ResponseBody
    public ResultMessage pay(@RequestParam("orderId") @NotNull int orderId,
                             @RequestParam("amount") @NotNull float amount) {
        designerOrderService.pay(orderId, amount);
        return ResultMessage.success();
    }

    @RequestMapping("/abort")
    @ResponseBody
    public ResultMessage abort(@RequestParam("orderId") @NotNull int orderId,
                      @RequestParam("cause") @NotNull String cause) {
        designerOrderService.abort(orderId, cause);
        return ResultMessage.success();
    }

    @RequestMapping("/requestCompletion")
    @ResponseBody
    public ResultMessage requestCompletion(@RequestParam("orderId") @NotNull int orderId) {
        designerOrderService.requestCompletion(orderId);
        return ResultMessage.success();
    }

    @RequestMapping("/confirmCompletion")
    @ResponseBody
    public ResultMessage confirmCompletion(@RequestParam("orderId") @NotNull int orderId) {
        designerOrderService.confirmCompletion(orderId);
        return ResultMessage.success();
    }

    @RequestMapping("/feedback")
    @ResponseBody
    public ResultMessage feedback(@RequestParam("orderId") @NotNull int orderId,
                                  @RequestParam("orderId") @NotNull int star,
                                  @RequestParam("orderId") @NotNull String description) {
        designerOrderService.feedback(orderId, star, description);
        return ResultMessage.success();
    }
}

