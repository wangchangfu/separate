package com.mapscience.modular.system.controller.excel;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.util.excel.EasyPOIExcelUtile;
import com.mapscience.modular.system.dto.EmployeeDTO;
import com.mapscience.modular.system.model.*;
import com.mapscience.modular.system.service.*;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * 操作表格
 */
@Controller
@RequestMapping("/excel")
public class ExcelController {

	/**
	 * 人员基本信息
	 */
	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private IEducationService educationService;
	
	@Autowired
	private IWorkHistoryService workHistoryService;
	
	@Autowired
	private IContactRelationshipService contactRelationshipService;
	
	/**
	 * 获取导出模板 /数据
	 * 
	 * @param request
	 * @param method  0 模板 1 数据
	 * @param type    导出模板类型 1 员工信息 2 教育经历 3 证书证件 4 工作经历
	 * @param ids     导出员工的ID
	 */
	@RequestMapping("getModelExcel")
	@ResponseBody
	public ResponseVal exportEmploy(HttpServletRequest request, Integer method, Integer type, String ids,
									HttpServletResponse response) {

		return new ResponseVal(200, "测试", null);
	}

	/**
	 * 导出空表格
	 * 
	 * @param response
	 * @return
	 */
	@RequestMapping("export")
	@ResponseBody
	public ResponseVal export(HttpServletResponse response) {
		EasyPOIExcelUtile.exportExcel(new ArrayList<EmployeeDTO>(), "员工基本信息", "基本信息", EmployeeDTO.class, "员工基本信息.xls",
				response);
		return new ResponseVal(200, "测试", null);
	}

	/**
	 * 导入excel信息
	 * 
	 * @param request
	 * @param files   innerText
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "importExcelEmp", method = RequestMethod.POST)
	public ResponseVal importExcelEmp(HttpServletRequest request,
			@RequestParam(value = "file", required = false) MultipartFile files) {
		try {
			// 获取workbook
			Workbook workBook = EasyPOIExcelUtile.getWorkBook(files);

			ImportParams params = new ImportParams();
			Sheet sh = null;
			// 循环工作表获取sheet
			for (int n = 0; n < workBook.getNumberOfSheets(); n++) {
				params.setTitleRows(1);
				params.setHeadRows(1);
				params.setStartSheetIndex(n);
				// 循环表获取页
				sh = workBook.getSheetAt(n);
				String sheetName = sh.getSheetName();
				if ("基本信息".equals(sheetName)) {
				    List<Employee> list = ExcelImportUtil.importExcel(files.getInputStream(), Employee.class, params);
				    employeeService.insertBatch(list);
				} else if ("教育".equals(sheetName)) {
					List<Education> list = ExcelImportUtil.importExcel(files.getInputStream(), Education.class, params);
					educationService.insertBatch(list);
				} else if ("工作经历".equals(sheetName)) {
					List<WorkHistory> list = ExcelImportUtil.importExcel(files.getInputStream(), WorkHistory.class, params);
					workHistoryService.insertBatch(list);
				} else if ("家庭成员社会关系".equals(sheetName)) {
					List<ContactRelationship> list = ExcelImportUtil.importExcel(files.getInputStream(), ContactRelationship.class, params);
					contactRelationshipService.insertBatch(list);
				}
			}
			return new ResponseVal(500, "success");
		} catch (Exception e) {
			return new ResponseVal(500, "erro");
		}

	}

	/**
	 * 导入excel公司
	 * @param files(标题0行，表头1行)
	 */
	@ApiOperation(value = "导入excel公司")
	@PostMapping("/importExcelCompany")
	@ResponseBody
	public ResponseVal importExcelCompany(@RequestParam(value = "file", required = false) MultipartFile files) {
		try {
			ImportParams params = new ImportParams();
		    params.setTitleRows(0);
		    params.setHeadRows(1);
		    List<Company> list = ExcelImportUtil.importExcel(files.getInputStream(), Company.class, params);
		    for (Company company : list) {
		    	company.setStatus(1);
		    	company.setCrateTime(new Date());
		        company.setUpdateTime(new Date());
			}
		    boolean insertBatch = companyService.insertBatch(list);
		    if(insertBatch) {
		    	return new ResponseVal(200, "success");
		    }else {
		    	return new ResponseVal(500, "fail");
		    }
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal(500, "erro");
		}
	}
	
	@ApiOperation(value = "导出员工信息模板")
	@RequestMapping("/exportEmployeeInfoTemplate")
	@ResponseBody
	public void exportEmployeeInfoTemplate(HttpServletResponse response){
		try {
			Map<String,Object> map = new HashMap<String, Object>() ;
			//本地资源
			TemplateExportParams params = new TemplateExportParams("d:/static/excelTemplate/员工信息模板.xlsx", true);
			//resource下资源
			//TemplateExportParams params = new TemplateExportParams("static/excelTemplate/员工信息模板.xlsx", true);
	        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
	        // 设置excel的文件名称
	        String excelName = "员工信息模板" ;
	        // 重置响应对象
	        response.reset();
	        // 指定下载的文件名--设置响应头
			response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(excelName, "UTF-8")+".xlsx");
	        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
	        response.setHeader("Pragma", "no-cache");
	        response.setHeader("Cache-Control", "no-cache");
	        response.setDateHeader("Expires", 0);
	        // 写出数据输出流到页面
            OutputStream output = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            workbook.write(bufferedOutPut);
            bufferedOutPut.flush();
            bufferedOutPut.close();
            output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
}
