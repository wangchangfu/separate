package com.mapscience.modular.system.controller.gateway;

import com.mapscience.core.getway.GetwayController;
import com.mapscience.modular.system.service.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 外部访问接口
 */
@Controller
@RequestMapping("gateway")
public class GatewayAction extends GetwayController {

    @Autowired
    private IMerchantService merchantService;

    @RequestMapping("geteway.ht")
    @ResponseBody
    public Object geteway(HttpServletRequest request, HttpServletResponse response, Map<String, Object> params){
        return super.geteWay(request, response, params, merchantService);
    }
}
