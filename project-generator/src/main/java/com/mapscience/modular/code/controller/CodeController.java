package com.mapscience.modular.code.controller;

import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.common.status.ProjectStatusEnum;
import com.mapscience.generator.action.config.WebGeneratorConfig;
import com.mapscience.generator.action.model.CodeProperties;
import com.mapscience.modular.code.factory.DefaultTemplateFactory;
import com.mapscience.modular.code.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "code")
public class CodeController {

    @Autowired
    private TableService tableService;

    //默认路径
    private final String PREFIX = "/modular/system/code/";

    @RequestMapping(value = "")
    public String blackboard(Model model) {
        model.addAttribute("tables", tableService.getAllTables());
        model.addAttribute("params", DefaultTemplateFactory.getDefaultParams());
        model.addAttribute("templates", DefaultTemplateFactory.getDefaultTemplates());
        return PREFIX + "code";
    }


    @ResponseBody
    @PostMapping(value = "/generate")
    public ResponseVal generate(CodeProperties codeProperties) {
        codeProperties.setUrl("jdbc:mysql://192.168.1.247:3306/zzsres?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=UTC");
        codeProperties.setUserName("root");
        codeProperties.setPassword("123456");
        WebGeneratorConfig webGeneratorConfig = new WebGeneratorConfig(codeProperties);
        webGeneratorConfig.doMpGeneration();
        return new ResponseVal(ProjectStatusEnum.SUCCESS.getCode(), ProjectStatusEnum.SUCCESS.getMsg());

    }


}
