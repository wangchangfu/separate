/**
 * 
 */
package com.mapscience.core.util;

import java.nio.charset.Charset;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.TypeReference;
import com.mapscience.core.util.common.StringUtil;

/**  

* <p>Title: HttpResult.java</p>  

* <p>Description: </p>  

* <p>Copyright: Copyright (c) 2018</p>  

* <p>Company: zzs</p>  

* @author wcf  

* @date 2018年8月8日  

* @version 1.0  

*/
public class HttpResult extends Result {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3548254366262658796L;

	/** 响应文本 */
	protected String text;

	/**
	 * HttpClient实例.
	 */
	protected HttpClient client;

	/**
	 * Http请求实例.
	 */
	protected HttpRequestBase request;

	/**
	 * Http 响应的 HttpResponse 实例对象.
	 */
	protected HttpResponse response;

	/**
	 * HttpClient会话上下文.
	 */
	protected HttpContext context;

	/**
	 * 零时存储的Cookie
	 */
	protected Map<String, Cookie> cookies;

	/**
	 * 请求时间毫秒数.
	 */
	protected Long requestTimes;

	/**
	 * 响应时间毫秒数.
	 */
	protected Long responseTimes;

	/**
	 * 构造方法.
	 */
	public HttpResult(Integer code, String error, Throwable cause) {
		this.responseTimes = System.currentTimeMillis();
	}

	/**
	 * 构造方法.
	 */
	public HttpResult(Integer code, String error, Throwable cause, Long requestTimes) {
		this.requestTimes = requestTimes;
		this.responseTimes = System.currentTimeMillis();
	}

	/**
	 * 构造方法.
	 */
	public HttpResult(HttpClient client, HttpRequestBase request, HttpResponse response, HttpContext context) {
		this.client = client;
		this.request = request;
		this.response = response;
		this.context = context;
		this.responseTimes = System.currentTimeMillis();
	}

	/**
	 * 构造方法.
	 */
	public HttpResult(HttpClient client, HttpRequestBase request, HttpResponse response, HttpContext context, Long requestTimes) {
		this.client = client;
		this.request = request;
		this.response = response;
		this.context = context;
		this.requestTimes = requestTimes;
		this.responseTimes = System.currentTimeMillis();
	}

	/**
	 * 构造方法.
	 */
	public HttpResult(HttpClient client, HttpRequestBase request, HttpResponse response, HttpContext context, Integer code,
			String error, Throwable cause) {
		this.client = client;
		this.request = request;
		this.response = response;
		this.context = context;
		this.responseTimes = System.currentTimeMillis();
	}

	/**
	 * 构造方法.
	 */
	public HttpResult(HttpClient client, HttpRequestBase request, HttpResponse response, HttpContext context, Integer code,
			String error, Throwable cause, Long requestTimes) {
		this.client = client;
		this.request = request;
		this.response = response;
		this.context = context;
		this.requestTimes = requestTimes;
		this.responseTimes = System.currentTimeMillis();
	}

	/**
	 * 获取 响应的状态码.
	 * 
	 * @return 响应的状态码.
	 */
	public Integer getStatusCode() {
		return (null != this.response ? this.response.getStatusLine().getStatusCode() : null);
	}

	/**
	 * 获取HttpResponse 响应的Cookie.
	 * 
	 * @return
	 */
	public Map<String, Cookie> getCookies() {
		if ((null == this.cookies || this.cookies.isEmpty()) && null != this.context) {
		}
		return this.cookies;
	}

	/**
	 * 判断 Response返回的头信息中是否存在指定Key的header.
	 * 
	 * @param key
	 * @return
	 */
	public boolean hasHeader(String key) {
		if (null == this.response) {
			return false;
		}
		return this.response.containsHeader(key);
	}

	/**
	 * 获取 指定Key对应的第一个Header.
	 * 
	 * @return Location响应头.
	 */
	public Header getHeader(String key) {
		return (null != this.response ? this.response.getFirstHeader(key) : null);
	}

	/**
	 * 获取 Response返回的所有Header.
	 * 
	 * @return Location响应头.
	 */
	public Header[] getHeaders() {
		return (null != this.response ? this.response.getAllHeaders() : null);
	}

	/**
	 * 获取 指定Key对应的多个Header.
	 * 
	 * @return Location响应头.
	 */
	public Header[] getHeaders(String key) {
		return (null != this.response ? this.response.getHeaders(key) : null);
	}

	/**
	 * 获取 Location响应头.
	 * 
	 * @return Location响应头.
	 */
	public Header[] getLocations() {
		return (null != this.response ? this.response.getHeaders("Location") : null);
	}

	/**
	 * 获取 HttpEntity.
	 * 
	 * @return HttpEntity.
	 */
	public HttpEntity getEntity() {
		return (null != this.response ? this.response.getEntity() : null);
	}

	/**
	 * 获取 响应的字符串内容.
	 * 
	 * @return String.
	 */
	public String getText() {
		return getText(null);
	}

	/**
	 * 获取 响应的字符串内容.
	 * 
	 * @return String.
	 */
	public String getText(Charset charset) {
		if (null != this.response) {
			String data = "";
			if (null == charset) {
				Header header = this.response.getFirstHeader("Content-Type");
				String contentType;
				int index;
				if (null != header && (index = (contentType = header.getValue()).indexOf("charset=")) != -1) {
					contentType = contentType.substring(index + 8).trim().replaceAll("\"", "");
					charset = Charset.forName(contentType);
				} else {
					charset = Charset.forName("UTF-8");
				}
			}
			try {
				if (null != response.getFirstHeader("Content-Encoding")
						&& response.getFirstHeader("Content-Encoding").getValue().equals("gzip")) {
					data = EntityUtils.toString(new GzipDecompressingEntity(response.getEntity()));
				} else {
					data = EntityUtils.toString(this.response.getEntity(), charset);
				}

				return this.text = data;
			} catch (Throwable e) {
				e.printStackTrace();
			} finally {
				this.request.releaseConnection();
			}
		}
		return null;
	}

	@Override
	/**
	 * 获取Response响应码
	 * 
	 * @return
	 */
	public int getCode() {
		return this.response.getStatusLine().getStatusCode();
	}

	/**
	 * 获取返回结果
	 */
	public Object getData() {
		return this.data = this.getText();
	}

	/**
	 * 将json格式字符串转为指定类型的Object;<br/>
	 * 当null==str或str为无效json格式字符串时, 则返回 null.<br/>
	 * 例：User dto = result.getData(new TypeReference&lt;User&gt;() {});
	 * 
	 * @param str
	 *            json格式字符串.
	 * @param clazz
	 *            对象的Class 类型
	 * @return T 对应Object实例.
	 */
	public <T> T getData(TypeReference<T> type) {
		String str;
		if (StringUtil.hasText(str = this.getText())) {
			try {
				return JSON.parseObject(str, type);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Long getUseTimes() {
		return (null != this.requestTimes ? (this.responseTimes - this.requestTimes) : null);
	}

	protected void finalize() throws Throwable {
		this.release();
		super.finalize();
	}

	/**
	 * 释放当前 HttpResponse、HttpRequest等所有资源.
	 */
	public void release() {
		this.cookies = null;
		this.context = null;
		if (null != this.client) {
			this.client = null;
			try {
				EntityUtils.consume(this.getEntity());
			} catch (Throwable e) {
			}

			if (null != this.response) {
				try {
					this.response = null;
				} catch (Throwable e) {
				}
			}

			if (null != this.request) {
				try {
					this.request.releaseConnection();
				} catch (Throwable e) {
				} finally {
					this.request = null;
				}
			}
		}
	}
}
