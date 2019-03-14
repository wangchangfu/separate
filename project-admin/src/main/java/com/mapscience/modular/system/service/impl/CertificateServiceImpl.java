package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.Certificate;
import com.mapscience.modular.system.model.WorkHistory;
import com.mapscience.modular.system.mapper.CertificateMapper;
import com.mapscience.modular.system.mapper.WorkHistoryMapper;
import com.mapscience.modular.system.service.ICertificateService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工证照表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
@Service
public class CertificateServiceImpl extends ServiceImpl<CertificateMapper, Certificate> implements ICertificateService {
	
	@Autowired
    private CertificateMapper mapper;

	@Override
	public List<Certificate> getCertificateByEmpId(String empId) {
		return mapper.getCertificateByEmpId(empId);
	}

}
