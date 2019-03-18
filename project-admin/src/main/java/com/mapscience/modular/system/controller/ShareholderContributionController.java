package com.mapscience.modular.system.controller;


import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.ShareholderContribution;
import com.mapscience.modular.system.service.IShareholderContributionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 股东出资表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@RestController
@RequestMapping("/shareholderContribution")
public class ShareholderContributionController {
	
	 @Autowired
	 private IShareholderContributionService service;
	
	@ApiOperation("添加股东出资信息")
    @PostMapping("/insertShareholderContribution")
    public ResponseVal insertShareholderContribution(ShareholderContribution entity){
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
    
	@ApiOperation("根据id删除股东出资信息")
	@DeleteMapping("/deleteShareholderContributionById")
	public ResponseVal deleteShareholderContributionById(String id) {
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
	
	@ApiOperation("修改股东出资信息")
	@PutMapping("/updateShareholderContribution")
	public ResponseVal updateShareholderContribution(ShareholderContribution entity) {
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
	
	@ApiOperation("根据Id查询股东出资信息")
	@GetMapping("/selectShareholderContributionById")
	public ResponseVal selectShareholderContributionById(String id) {
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

