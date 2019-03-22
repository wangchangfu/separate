package com.mapscience.modular.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mapscience.modular.system.model.Company;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 公司基本信息表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
public interface CompanyMapper extends BaseMapper<Company> {

    /**
     * 查找公司
     *
     * @return
     */
    List<Company> getList();

    /**
     * 保存
     *
     * @param company
     * @return
     */
    String saveCompany(Company company);

    /**
     * 根据管理员ID查找公司
     *
     * @param employeeId
     * @return
     */
    @Select("select c.* from t_company c join t_emp_position e on e.Com_id=c.company_id where e.emp_id=#{employeeId}")
    List<Company> findComByEmp(String employeeId);

    /**
     * 根据ID查询公司
     *
     * @param id
     * @return
     */
    @Select("select * from t_company where `status`=1 and company_id='1' ")
    Company findComById(String id);

    /**
     * 查询全部
     *
     * @param company
     * @return
     */
    List<Company> findComList(Company company);


    /**
     * 根据公司名查询
     *
     * @param companyName
     * @return
     */
    Company getCompanyByCompanyName(String companyName);


    /**
     * 根据公司描述查询
     *
     * @param remark
     * @return
     */
    Company getCompanyByRemark(String remark);


    /**
     * 根据id查询公司
     * @param comId
     * @return
     */
    List<Company> getCompanyById(String comId);


}
