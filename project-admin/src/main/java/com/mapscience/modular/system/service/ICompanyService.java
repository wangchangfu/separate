package com.mapscience.modular.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.Company;

import java.util.List;

/**
 * <p>
 * 公司基本信息表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
public interface ICompanyService extends IService<Company> {

    /**
     * 查询所有公司
     *
     * @return
     */
    public List<Company> getList();

    /**
     * 查询公司树
     *
     * @param company
     * @return
     */
    public ResponseVal<List<Company>> findComTree(Company company);

    /**
     * 保存
     *
     * @param company
     * @return
     */
    public ResponseVal saveCompany(Company company);

    /**
     * 根据员工ID查询公司
     *
     * @param employeeId
     * @return
     */
    List<Company> findComByEmp(String employeeId);

    Company findComById(String s);

    /**
     * 查询全部
     *
     * @param company
     * @return
     */
    ResponseVal findComList(Company company);

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

    List<Object> findCompanyAndDepartmentTree();

    /**
     * 根据id查询公司
     * @param comId
     * @return
     */
    List<Company> getCompanyById(String comId);


}
