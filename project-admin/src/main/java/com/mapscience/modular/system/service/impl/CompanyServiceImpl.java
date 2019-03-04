package com.mapscience.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.mapper.CompanyMapper;
import com.mapscience.modular.system.model.Company;
import com.mapscience.modular.system.service.ICompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    /**
     * 查找所有的公司
     * @return
     */
    @Override
    public List<Company> getList(){
        return  this.baseMapper.getList();
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
}
