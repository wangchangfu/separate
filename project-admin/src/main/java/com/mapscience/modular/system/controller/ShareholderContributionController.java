package com.mapscience.modular.system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.ShareholderContribution;
import com.mapscience.modular.system.service.IShareholderContributionService;

/**
 * <p>
 * 股东出资表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@Controller
@RequestMapping("/shareholderContribution")
public class ShareholderContributionController {
	
	 @Autowired
	 private IShareholderContributionService service;
	
    /**
     * 增加
     */
    @ResponseBody
    @RequestMapping("insert")
    public ResponseVal insert(ShareholderContribution entity){
		try {
			boolean flag = service.insert(entity);
			if(flag) {
				return new ResponseVal(200,"success");
			}else {
				return new ResponseVal(500,"fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal(500,"erro");
		}
    }
    
	/**
	 * 根据id删除
	 */
	@RequestMapping(value = "deleteById")
	@ResponseBody
	public ResponseVal deleteById(String id) {
		try {
			boolean flag = service.deleteById(id);
			if(flag) {
				return new ResponseVal(200,"success");
			}else {
				return new ResponseVal(500,"fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal(500,"erro");
		}
	}
	
	/**
	 * 根据id修改
	 */
	@RequestMapping(value = "updateById")
	@ResponseBody
	public ResponseVal updateById(ShareholderContribution entity) {
		try {
			boolean flag = service.updateById(entity);
			if(flag) {
				return new ResponseVal(200,"success");
			}else {
				return new ResponseVal(500,"fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal(500,"erro");
		}
	}
	
	/**
	 * 根据Id查询
	 */
	@RequestMapping(value = "selectById")
	@ResponseBody
	public ResponseVal selectById(String id) {
		try {
			ShareholderContribution selectById = service.selectById(id);
			if(ObjectUtils.isEmpty(selectById)) {
				return new ResponseVal(500,"fail",null);
			}else {
				return new ResponseVal(200,"success",selectById);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal(500,"erro",null);
		}
	}

}

