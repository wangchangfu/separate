package com.mapscience.modular.system.mapper;

import com.mapscience.modular.system.model.Certificate;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 员工证照表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-18
 */
public interface CertificateMapper extends BaseMapper<Certificate> {
	
	List<Certificate> getCertificateByEmpId(String empId);

}
