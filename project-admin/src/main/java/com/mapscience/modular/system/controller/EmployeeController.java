package com.mapscience.modular.system.controller;


import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.Employee;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 员工信息表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    public ResponseVal login(Employee employee){

        return null;
    }

}

