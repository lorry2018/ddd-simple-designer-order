package com.screw.web.controller;

import com.screw.service.DesignerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/designer", produces = "application/json;charset=utf-8")
public class DesignerController {
    @Autowired
    private DesignerService designerService;

    @RequestMapping("/enable")
    @ResponseBody
    public ResultMessage enable(@RequestParam("designerId") @NotNull int designerId) {
        designerService.enable(designerId);
        return ResultMessage.success();
    }

    @RequestMapping("/disable")
    @ResponseBody
    public ResultMessage disable(@RequestParam("designerId") @NotNull int designerId) {
        designerService.disable(designerId);
        return ResultMessage.success();
    }
}
