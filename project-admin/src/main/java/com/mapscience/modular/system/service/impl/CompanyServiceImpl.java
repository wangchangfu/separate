package com.mapscience.modular.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.mapper.CompanyMapper;
import com.mapscience.modular.system.model.Company;
import com.mapscience.modular.system.model.Department;
import com.mapscience.modular.system.service.ICompanyService;
import com.mapscience.modular.system.service.IDepartmentService;

/**
 * <p>
 * 公司基本信息表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {

    private Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);
    
    @Autowired
	private IDepartmentService departmentservice;

    /**
     * 查找所有的公司
     * @return
     */
    @Override
    public List<Company> getList(){
        return  this.baseMapper.getList();
    }

    /**
     * 查询公司信息
     * @param company
     * @return
     */
    @Override
    public ResponseVal findComList(Company company) {
        try{
            //查询所有公司
            List<Company> comList = this.baseMapper.findComList(company);
            //将list转换为tree
            List<Company> bulid = bulid(comList);
            return new ResponseVal(HttpStatus.OK.value(),"查询成功",bulid);
        }catch (Exception e){
            return new ResponseVal(HttpStatus.INTERNAL_SERVER_ERROR.value(),"查询错误",e.getMessage());
        }

    }
    /**
     * 查询公司树
     * @param company
     * @return
     */
    @Override
    public ResponseVal findComTree(Company company) {
        try{
        //查询所有的公司
        List<Company> list = this.getList();

        //将list转为tree
        List<Company> bulid = bulid(list);
        return new ResponseVal(200,"查询成功",bulid);
        }catch (Exception e){
            logger.error("查询公司树出错："+e);
            return new ResponseVal(500,"查询出错");
        }

    }

    /**
     * 保存
     * @param company
     * @return
     */
    @Override
    public ResponseVal saveCompany(Company company) {
        try {
            //
            company.setCrateTime(new Date());
            company.setUpdateTime(new Date());
            String s = this.baseMapper.saveCompany(company);

            return new ResponseVal(200, "保存成功", s);

        } catch (Exception e) {
        return new ResponseVal(500, "保存错误",e);
        }
    }

    /**
     * 递归查找公司
     * @param treeNodes
     * @return
     */
    public List<Company> bulid(List<Company> treeNodes){
        List<Company> trees= new ArrayList<Company>();
        for (Company t:treeNodes){
            if("0".equals(t.getParentId())){
                trees.add(findChildre(t,treeNodes));
            }
        }
        return trees;
    }

    /**
     * 内部调用查找下级公司方法
     * @param t
     * @param treeNodes
     * @return
     */
    private Company findChildre(Company t, List<Company> treeNodes) {
        t.setChildren(new ArrayList<Company>());
        for (Company me: treeNodes){
            if(t.getCompanyId().equals(me.getParentId())){
                if(t.getChildren()==null){
                    t.setChildren(new ArrayList<Company>());
                }
                t.getChildren().add(findChildre(me,treeNodes));
            }
        }
        return t;
    }


    /**
     * 根据管理员ID查找所在公司信息
     * @param employeeId
     * @return
     */
    @Override
    public List<Company> findComByEmp(String employeeId) {
        List<Company> comByEmp = this.baseMapper.findComByEmp(employeeId);
        if (!comByEmp.isEmpty()){
            return comByEmp;

        }
        return null;
    }

    /**
     * 根据ID查询公司
     * @param
     * @return
     */
    @Override
    public Company findComById(String id) {
        return this.baseMapper.findComById(id);
    }

	@Override
	public List<Object> findCompanyAndDepartmentTree() {
		List<Object> list = Lists.newArrayList();
		//查询所有公司
		List<Company> aLLCompanyList = this.selectList(null);
		//查询所有部门
		List<Department> allDepartmentList = departmentservice.selectList(null);
		for (Company company : aLLCompanyList) {
			if(ObjectUtils.isEmpty(company.getParentId())) {
				list.add(company);
				addObjectChidren(company, aLLCompanyList, allDepartmentList);
			}
		}
		return list;
	}
	
	private void addObjectChidren(Object thisObject, List<Company> aLLCompanyList, List<Department> allDepartmentList) {
		ArrayList<Object> objectList = Lists.newArrayList();
		Company thisCompany = null;
		Department thisDepartment = null;
		
		if(thisObject instanceof Company) {
			thisCompany = (Company)thisObject;
			String thisCompanyId = thisCompany.getCompanyId();
			for (Department department : allDepartmentList) {
				if(ObjectUtils.isEmpty(department.getParentId()) && thisCompanyId.equals(department.getCompanyId())) {
					objectList.add(department);
				}
			}
			for (Company company : aLLCompanyList) {
				if(thisCompanyId.equals(company.getParentId())) {
					objectList.add(company);
				}
			}
			thisCompany.setObjectChildren(objectList);
			if(!ObjectUtils.isEmpty(objectList)) {
				for (Object object : objectList) {
					addObjectChidren(object, aLLCompanyList, allDepartmentList);
				}
			}
		}
		if(thisObject instanceof Department) {
			thisDepartment = (Department)thisObject;
			String thisDepartmentId = thisDepartment.getDepartmentId();
			for (Department department : allDepartmentList) {
				if(thisDepartmentId.equals(department.getParentId())) {
					objectList.add(department);
				}
			}
			thisDepartment.setObjectChildren(objectList);
			if(!ObjectUtils.isEmpty(objectList)) {
				for (Object object : objectList) {
					addObjectChidren(object, aLLCompanyList, allDepartmentList);
				}
			}
		}
		
	}
	
}
