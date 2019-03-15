package com.mapscience.modular.system.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.mapscience.modular.system.model.Certificate;

/**
 * <p>
 * 员工证照表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface ICertificateService extends IService<Certificate> {
	
	List<Certificate> getCertificateByEmpId(String empId);

}
