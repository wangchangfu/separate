package com.mapscience.modular.system.controller.excel;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.util.excel.EasyPOIExcelUtile;
import com.mapscience.modular.system.dto.EmployeeDTO;
import com.mapscience.modular.system.service.IEmployeeService;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * 操作表格
 */
@Controller
@RequestMapping("excel")
public class ExcelController {

    /**
     * 人员基本信息
     */
    @Autowired
    private IEmployeeService employeeService;


    /**
     *获取导出模板 /数据
     * @param request
     * @param method  0 模板  1 数据
     * @param type  导出模板类型  1 员工信息 2 教育经历 3 证书证件 4  工作经历
     * @param ids   导出员工的ID
     */
    @RequestMapping("getModelExcel")
    @ResponseBody
    public ResponseVal exportEmploy(HttpServletRequest request, Integer method, Integer type, String ids, HttpServletResponse response){

        return new ResponseVal(200,"测试",null);
    }

    /**
     *  导出空表格
     * @param response
     * @return
     */
    @RequestMapping("export")
    @ResponseBody
    public ResponseVal export( HttpServletResponse response){
        EasyPOIExcelUtile.exportExcel(new ArrayList<EmployeeDTO>(),"员工基本信息","基本信息",EmployeeDTO.class,"员工基本信息.xls",response);
        return new ResponseVal(200,"测试",null);
    }


    /**
     * 导入excel信息
     * @param request
     * @param files
     * innerText
     * @return
     */
   @ResponseBody
   @RequestMapping(value = "importExcelEmp" ,method=RequestMethod.POST)
    public ResponseVal importExcelEmp(HttpServletRequest request, @RequestParam(value = "file", required = false)MultipartFile files) {
        try {
            //获取workbook
            Workbook workBook = EasyPOIExcelUtile.getWorkBook(files);

            ImportParams params = new ImportParams();
            Sheet sh=null;
            //循环工作表获取sheet
            for (int n = 0; n < workBook.getNumberOfSheets(); n++) {
                //表头在第几行
                params.setTitleRows(0);
                params.setStartSheetIndex(n);
                ExcelImportResult<Object> result = null;
             //循环表获取页
                sh=workBook.getSheetAt(n);
                String sheetName = sh.getSheetName();
                if ("基本信息".equals(sheetName)){
                  result=ExcelImportUtil.importExcelMore(files.getInputStream(),EmployeeDTO.class,params) ;

                }else if ("教育".equals(sheetName)){
                    result=ExcelImportUtil.importExcelMore(files.getInputStream(),EmployeeDTO.class,params) ;
                }else if ("工作经历".equals(sheetName)){

                }else if ("家庭成员社会关系".equals(sheetName)){

                }

            }


            return null;
            
        }catch (Exception e){
            return new ResponseVal(500,"上传失败！");
        }

   }

}
