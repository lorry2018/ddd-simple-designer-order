package com.screw.web.controller;

import com.screw.service.DesignerOrderService;
import com.screw.domain.order.DesignerOrder;
import com.screw.domain.order.DesigningProgressNodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order", produces = "application/json;charset=utf-8")
public class DesignerOrderController {
    @Autowired
    private DesignerOrderService designerOrderService;

    @RequestMapping("/selectByKey")
    @ResponseBody
    public ResultMessage create(@RequestParam("orderId") int orderId) {
        return ResultMessage.success(designerOrderService.selectByKey(orderId));
    }

    @RequestMapping("/create")
    @ResponseBody
    public ResultMessage create(@RequestParam("customerId") int customerId,
                                int designerId) {
        DesignerOrder order = designerOrderService.createOrder(customerId, designerId);
        return ResultMessage.success(order);
    }

    @RequestMapping("/measure")
    @ResponseBody
    public ResultMessage measure(@RequestParam("orderId") int orderId,
                               @RequestParam("area") float area) {
        designerOrderService.measure(orderId, area);
        return ResultMessage.success();
    }

    @RequestMapping("/quote")
    @ResponseBody
    public ResultMessage quote(@RequestParam("orderId") int orderId,
                      @RequestParam("expectedAmount") float expectedAmount,
                      @RequestParam(value = "estimatedDaysList[]", required = false) int[] estimatedDaysList) {
        estimatedDaysList = new int[] {1, 4, 4, 1};

        designerOrderService.quote(orderId, expectedAmount, estimatedDaysList);
        return ResultMessage.success();
    }

    @RequestMapping("/acceptQuote")
    @ResponseBody
    public ResultMessage acceptQuote(@RequestParam("orderId") int orderId) {
        designerOrderService.acceptQuote(orderId);
        return ResultMessage.success();
    }

    @RequestMapping("/rejectQuote")
    @ResponseBody
    public ResultMessage rejectQuote(@RequestParam("orderId") int orderId) {
        designerOrderService.rejectQuote(orderId);
        return ResultMessage.success();
    }

    @RequestMapping("/pay")
    @ResponseBody
    public ResultMessage pay(@RequestParam("orderId") int orderId,
                             @RequestParam("amount") float amount) {
        designerOrderService.pay(orderId, amount);
        return ResultMessage.success();
    }

    @RequestMapping("/abort")
    @ResponseBody
    public ResultMessage abort(@RequestParam("orderId") int orderId,
                      @RequestParam("cause") String cause) {
        designerOrderService.abort(orderId, cause);
        return ResultMessage.success();
    }

    @RequestMapping("/requestCompletionForProgressNode")
    @ResponseBody
    public ResultMessage requestCompletionForProgressNode(@RequestParam("orderId") int orderId,
                                           @RequestParam("nodeType") int nodeTypeCode,
                                           @RequestParam("achievement") String achievement) {
        designerOrderService.requestCompletionForProgressNode(orderId, convertToNodeType(nodeTypeCode), achievement);
        return ResultMessage.success();
    }

    private DesigningProgressNodeType convertToNodeType(int nodeTypeCode) {
        DesigningProgressNodeType nodeType = DesigningProgressNodeType.FLOORPLAN_DESIGN;
        if (DesigningProgressNodeType.FLOORPLAN_DESIGN.getCode() == nodeTypeCode) {
            nodeType = DesigningProgressNodeType.FLOORPLAN_DESIGN;
        } else if (DesigningProgressNodeType.SKETCH_DESIGN.getCode() == nodeTypeCode) {
            nodeType = DesigningProgressNodeType.SKETCH_DESIGN;
        } else if (DesigningProgressNodeType.CONSTRUCTION_DRAWING_DESIGN.getCode() == nodeTypeCode) {
            nodeType = DesigningProgressNodeType.CONSTRUCTION_DRAWING_DESIGN;
        } else if (DesigningProgressNodeType.DISCLOSURE.getCode() == nodeTypeCode) {
            nodeType = DesigningProgressNodeType.DISCLOSURE;
        }
        return nodeType;
    }

    @RequestMapping("/confirmCompletionForProgressNode")
    @ResponseBody
    public ResultMessage confirmCompletionForProgressNode(@RequestParam("orderId") int orderId,
                                                          @RequestParam("nodeType") int nodeTypeCode) {
        designerOrderService.confirmCompletionForProgressNode(orderId, convertToNodeType(nodeTypeCode));
        return ResultMessage.success();
    }

    @RequestMapping("/refund")
    @ResponseBody
    public ResultMessage refund(@RequestParam("orderId") int orderId,
                                @RequestParam("cause") String cause) {
        designerOrderService.refund(orderId, cause);
        return ResultMessage.success();
    }

    @RequestMapping("/feedback")
    @ResponseBody
    public ResultMessage feedback(@RequestParam("orderId") int orderId,
                                  @RequestParam("orderId") int star,
                                  @RequestParam("orderId") String description) {
        designerOrderService.feedback(orderId, star, description);
        return ResultMessage.success();
    }

    @RequestMapping("/completeRefund")
    @ResponseBody
    public ResultMessage completeRefund(@RequestParam("refundOrderId") int refundOrderId) {
        designerOrderService.completeRefundOrder(refundOrderId);
        return ResultMessage.success();
    }
}

