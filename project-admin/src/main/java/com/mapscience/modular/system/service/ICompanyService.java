package com.mapscience.modular.system.service;

import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.Company;
import com.baomidou.mybatisplus.service.IService;

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
    public ResponseVal findComTree(Company company);

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

    ResponseVal findComList(Company company);

    Company getCompanyByCompanyName(String companyName);

    Company getCompanyByRemark(String remark);
}
