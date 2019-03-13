package com.mapscience.modular.system.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.common.constant.Constant;
import com.mapscience.core.common.status.ProjectStatusEnum;
import com.mapscience.core.exception.ProjectException;
import com.mapscience.core.util.AesCipherUtil;
import com.mapscience.core.util.JedisUtil;
import com.mapscience.core.util.JwtUtil;
import com.mapscience.modular.system.model.Employee;
import com.mapscience.modular.system.service.IEmployeeService;

import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 员工信息表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-01-16
 */
@RestController
@RequestMapping("/employee")
@PropertySource("classpath:jwt.properties")
public class EmployeeController {
	/**
	 * RefreshToken过期时间
	 */
	@Value("${refreshTokenExpireTime}")
	private String refreshTokenExpireTime;

	@Autowired
	private IEmployeeService employeeService;

	/**
	 * 用户登录
	 * 
	 * @param employee
	 * @return
	 */
	@PostMapping("/login")
	public ResponseVal login(@RequestBody Employee employee) {
		// 验证是否为空
		if (ObjectUtils.isEmpty(employee.getAccount())) {
			return new ResponseVal(ProjectStatusEnum.NO_THIS_USER.getCode(), ProjectStatusEnum.NO_THIS_USER.getMsg());
		}
		if (ObjectUtils.isEmpty(employee.getPassWord())) {
			return new ResponseVal(ProjectStatusEnum.USE_PASSWORD_NO.getCode(),
					ProjectStatusEnum.USE_PASSWORD_NO.getMsg());
		}
		// 清除空格
		employee.setAccount(employee.getAccount().trim());
		employee.setPassWord(employee.getPassWord().trim());
		String key = AesCipherUtil.enCrypto(employee.getPassWord());
		/**
		 * 查找用户
		 */
		Employee emp = this.employeeService.getEmployeeByAccountAndPasswd(employee.getAccount(), key);
		if (ObjectUtils.isEmpty(emp)) {
			return new ResponseVal(ProjectStatusEnum.USER_NOT_EXISTED.getCode(),
					ProjectStatusEnum.USER_NOT_EXISTED.getMsg());
		}
		// 清除可能存在的Shiro权限信息缓存
		if (JedisUtil.exists(Constant.PREFIX_SHIRO_CACHE + emp.getAccount())) {
			JedisUtil.delKey(Constant.PREFIX_SHIRO_CACHE + emp.getAccount());
		}
		// 设置RefreshToken，时间戳为当前时间戳，直接设置即可(不用先删后设，会覆盖已有的RefreshToken)
		String currentTimeMillis = String.valueOf(System.currentTimeMillis());
		JedisUtil.setObject(Constant.PREFIX_SHIRO_REFRESH_TOKEN + emp.getAccount(), currentTimeMillis,
				Integer.parseInt(refreshTokenExpireTime));
		// 从Header中Authorization返回AccessToken，时间戳为当前时间戳
		String token = JwtUtil.sign(emp.getAccount(), currentTimeMillis);
		return new ResponseVal(HttpStatus.OK.value(), "登录成功");
	}

	@ApiOperation(value = "增加员工（目前存在问题：密码加密解密）")
	@PostMapping("/postEmployee")
	public ResponseVal postEmployee(Employee entity) {
		try {
			// 查询人员是否添加 按照名称+身份证号查询
			Employee byAccount = this.employeeService.getByAccount(entity);

			if (byAccount != null) {
				throw new ProjectException(ProjectStatusEnum.USER_ALREADY_REG);
			}
			if (entity.getPassWord().length() > Constant.PASSWORD_MAX_LEN) {
				throw new ProjectException(ProjectStatusEnum.PASSWORD_OVER_LENGTH);
			}

			entity.setCrateTime(new Date());
			// 密码加密
			String s = AesCipherUtil.enCrypto(entity.getAccount() + entity.getPassWord());
			entity.setPassWord(s);
			boolean insert = this.employeeService.insert(entity);
			if (insert) {
				return new ResponseVal(200, "success");
			} else {
				return new ResponseVal(500, "fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal(500, "erro");
		}
	}

	@ApiOperation(value = "修改员工")
	@PutMapping("/putEmployee")
	public ResponseVal updateEmployee(Employee entity) {
		try {
			boolean updateById = employeeService.updateAllColumnById(entity);
			if (updateById) {
				return new ResponseVal(200, "success");
			} else {
				return new ResponseVal(500, "fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal(500, "erro");
		}
	}

	@ApiOperation(value = "通过id查询员工")
	@GetMapping("/getEmployeeById")
	public ResponseVal findEmployeeById(String id) {
		try {
			Employee selectById = employeeService.selectById(id);
			if (ObjectUtils.isEmpty(selectById)) {
				return new ResponseVal(500, "fail", null);
			} else {
				return new ResponseVal(200, "success", selectById);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal(500, "erro", null);
		}
	}

	@ApiOperation(value = "模糊查询")
	@GetMapping("/fuzzyQuery")
	public ResponseVal fuzzyQuery(String comId, String empName, String tel, String starWorkTime, String endWorkTime, String startBirthTime, String endBirthTime, String education) {
		try {
			List<Employee> fuzzyQuery = employeeService.fuzzyQuery(comId, empName, tel, starWorkTime, endWorkTime, startBirthTime, endBirthTime, education);
			return new ResponseVal(200, "success", fuzzyQuery);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal(500, "erro", null);
		}
	}
	
	@ApiOperation(value = "通过公司id查询员工")
	@GetMapping("/getEmployeeByCompanyId")
	public ResponseVal getEmployeeByCompanyId(String companyId) {
		try {
			List<Employee> getEmployeeByCompanyId = employeeService.getEmployeeByCompanyId(companyId);
			return new ResponseVal(200, "success", getEmployeeByCompanyId);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal(500, "erro", null);
		}
	}
	
	@ApiOperation(value = "通过id批量删除员工")
	@GetMapping("/batchDeleteEmployeeById")
	public ResponseVal batchDeleteEmployeeByIds(String ids) {
		try {
			employeeService.batchDeleteEmployeeStatusByIds(ids);
			return new ResponseVal(200, "success");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVal(500, "erro");
		}
	}
	

}