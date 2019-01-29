package com.mapscience.modular.system.controller;


import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.Health;
import com.mapscience.modular.system.service.IHealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 健康状况表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@Controller
@RequestMapping("/health")
public class HealthController {

    @Autowired
    private IHealthService healthService;

    @ResponseBody
    @GetMapping("/getList")
    public ResponseVal getList(){
        List<Health> list = healthService.getList();
        return new ResponseVal(200, "查询成功", list);
    }

}

