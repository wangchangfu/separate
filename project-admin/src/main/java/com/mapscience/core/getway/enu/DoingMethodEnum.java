/**
 * 
 */
package com.mapscience.core.getway.enu;

/**
 *说明：
 *<p>该类是注册统一对外开放接口METHD参数 常量为BEAN定义的名称，例如:</p>
 * 书写者 @author  WCF
 * 创建时间 2018年12月3日
 *
 */
public enum DoingMethodEnum {
	/**列表查询*/
	EMP_QUERY("1","通道列表查询"),
	/**列表查询*/
	EMP_ADD("2","员工增加");
	/*******************************************基础***********************************************/
	private String menuCode;//菜单Code
	private String menuName;//菜单名称
	private Class<?> rqtClasses;//对应请求的对象对象
	private String method;//方法具体名称
	DoingMethodEnum(String menuCode,String menuName){this.menuCode = menuCode;this.menuName = menuName;}
	public Class<?> getRqtClasses() {return rqtClasses;}
	public final void setRqtClasses(Class<?> rqtClasses) {this.rqtClasses = rqtClasses;}
	public final void setMethod(String method) {this.method = method;}
	public final String getMenuCode() {return menuCode;}
	public final String getMenuName() {return menuName;}
	public final String getMethod(){return method;}
	/*******************************************基础***********************************************/
}
