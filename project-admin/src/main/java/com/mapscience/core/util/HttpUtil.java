package com.mapscience.core.util;

import com.mapscience.core.util.common.StringUtil;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.pool.PoolStats;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * <pre>
 * <b>通用HttpClient辅助工具.</b>
 * <b>Description:</b> 
 * <b>Changelog:</b> 
 *   ----------------------------------------------------------------------
 * </pre>
 */
@SuppressWarnings("deprecation")
public abstract class HttpUtil {

	// 线程池维护线程所允许的空闲时间的单位, 默认: MILLISECONDS.
	// private static TimeUnit time_unit = TimeUnit.MILLISECONDS;

	// 回收超时的Socket实例（单位毫秒）, 默认：15分钟.
	// private static int time_to_live = 1000 * 60 * 5;

	/** 连接池的最大连接个数, 默认: 256. */
	private static int max_to_tal = 256;

	/** 每个路由上的默认并发连接个数, 默认: 32. */
	private static int max_perroute = 32;

	// Httpclient不使用NoDelay策略, 默认：false.
	private static boolean tcp_nodelay = true;

	// Httpclient版本
	private static String useragent = "Apache-HttpClient/4.3.3 (java 1.5)";

	// Httpclient内容字符集
	private static String content_charset = "ISO-8859-1";

	private static Charset CHARSET = Charset.forName("UTF-8");

	// Httpclient重定向是否应该自动处理
	private static boolean handle_redirects = false;

	// Socket缓冲区的大小（单位为字节）, 默认: 1024 * 1024 * 8 = 8KB.
	// private static int buffer_size = 1024 * 1024 * 1;

	// 建立Socket连接最大等待时间（单位毫秒）, 默认：8秒.
	// 连接最大等待时间则是指和站点建立连接时的最大等待时间, 超过这个时间站点不给回应, 则认为站点无法连接.
	private static int so_timeout = 1000 * 8;

	// 数据下载最大等待时间（单位毫秒）, 默认：30秒.
	// 等待时间是指从站点下载页面和数据时, 两个数据包之间的最大时间间隔, 超过这个时间间隔, httpclient就认为连接出了故障.
	private static int conn_timeout = 1000 * 16;

	// 请求连接池分配连接实例最大等待时间（单位毫秒）, 默认：10秒.
	private static int conn_req_timeout = 1000 * 30;

	// HttpClient连接池管理器.
	private static PoolingClientConnectionManager cm = null;

	// 用于为每个调用线程绑定不同的HttpResult实例.
	private static ThreadLocal<HttpResult> httpResultHolder;

	static {
		// HTTP
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));

		// HTTPS
		try {
			final X509Certificate[] _AcceptedIssuers = new X509Certificate[] {};
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {
				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return _AcceptedIssuers;
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}
			};
			ctx.init(null, new TrustManager[] { tm }, new SecureRandom());
			SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			schemeRegistry.register(new Scheme("https", 443, ssf));
		} catch (Exception e) {
		}

		cm = new PoolingClientConnectionManager(schemeRegistry);
		cm.setMaxTotal(max_to_tal);
		cm.setDefaultMaxPerRoute(max_perroute);

		// 为具体ping路由设置最大并发连接数 2个.
		HttpHost ping = new HttpHost("www.baidu.com", 80);
		cm.setMaxPerRoute(new HttpRoute(ping), 2);

		// 初始化本地线程池绑定HttpResult容器.
		httpResultHolder = new ThreadLocal<HttpResult>();
	}

	/**
	 * 受保护的构造方法, 防止外部构建对象实例.
	 */
	protected HttpUtil() {
		super();
	}

	/**
	 * 清理Http连接池中才无效链接.
	 */
	public void clean() {
		if (null != cm) {
			// 这里的清理是将HttpClient连接管理器中持有的过期连接回收释放掉.
			cm.closeExpiredConnections();
		}
	}

	/**
	 * 获取当前工具的状态.
	 * 
	 * @return Map<String, Object>
	 */
	public Map<String, Object> status() {
		// 构建Map, 将连接池中的相关状态数据进行持有.
		Map<String, Object> status = new HashMap<String, Object>(4);
		if (null != cm) {
			// 从HttpClient连接管理器中获取连接池的状态实例.
			PoolStats poolStats = cm.getTotalStats();
			status.put("pool.maxTotal", cm.getMaxTotal());
			status.put("pool.defaultMaxPerRoute", cm.getDefaultMaxPerRoute());
			status.put("pool.max", poolStats.getMax());
			status.put("pool.available", poolStats.getAvailable());
			status.put("pool.leased", poolStats.getLeased());
			status.put("pool.pending", poolStats.getPending());
		}
		// 返回当前状态数据.
		return status;
	}

	/**
	 * 释放当前资源.
	 */
	public void release() {
		if (null != cm) {
			// 关闭当前HttpClient连接管理器.
			cm.shutdown();
		}
	}

	/**
	 * 根据参数构建定制的 HttpClient实例.
	 * 
	 * @param proxy
	 *            代理路由.
	 * @param soTimeout
	 *            建立Socket连接最大等待时间（单位毫秒）.
	 * @param connTimeout
	 *            数据下载最大等待时间（单位毫秒）.
	 * @param certPassword
	 *            SSL证书密码.
	 * @param certPath
	 *            SSL证书路径.
	 * @return CloseableHttpClient
	 */
	private static final HttpClient _bulidClient(HttpProxy proxy, Integer soTimeout, Integer connTimeout, String certPath,
			String certPassword) {
		// 判断当前HttpClient连接管理器是否已经初始化, 否则返回null.
		if (null == cm) {
			return null;
		}

		// 判断设置 建立Socket连接最大等待时间 和 数据下载最大等待时间 是否有效并且必须大于等于4秒.
		// 否则自动采用当前默认参数配置.
		soTimeout = (null != soTimeout && soTimeout >= so_timeout) ? soTimeout : so_timeout;
		connTimeout = (null != connTimeout && connTimeout >= conn_timeout) ? connTimeout : conn_timeout;
		// 配置RequestConfig 相关配置.
		HttpParams requestConfig = new BasicHttpParams();
		requestConfig.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connTimeout);
		requestConfig.setParameter(CoreConnectionPNames.SO_TIMEOUT, conn_req_timeout);
		requestConfig.setParameter("http.useragent", useragent);
		requestConfig.setParameter("http.protocol.content-charset", content_charset);
		requestConfig.setParameter("http.tcp.nodelay", tcp_nodelay);
		requestConfig.setParameter("http.protocol.handle-redirects", handle_redirects);
		return new DefaultHttpClient(cm, requestConfig);
	}

	/**
	 * 构建HTTP请求对象实例.<br/>
	 * 该函数可以构建get、post（支持多文件上传）形式的HTTP请求.
	 * 
	 * @param method
	 *            请求方式, 支持：get、post.
	 * @param url
	 *            请求地址.
	 * @param headers
	 *            请求头信息集合, 采用Key-Value形式提供, Key将作为每项头信息的Name.
	 * @param params
	 *            post表单参数集合, 采用Key-Value形式提供, Key将作为表单项的名称.
	 * @param charset
	 *            参数转换的编码, 如果不提供或无效, 则采用默认 _Util.ENCODING=UTF-8.
	 * @return HttpRequestBase
	 */
	private static final HttpRequestBase _bulidRequest(String key, Method method, String url, Map<String, String> headers,
			Map<String, Object> params, Charset charset) {
		HttpRequestBase request = null;

		// 判断当前是为get 请求, 即构建get请求.
		if (method.equals(Method.GET)) {
			StringBuilder sbQuery = new StringBuilder();
        	for (Entry<String, Object> query : params.entrySet()) {
        		if (0 < sbQuery.length()) {
        			sbQuery.append("&");
        		}
        		if (ObjectUtil.isEmpty(query.getKey()) && !ObjectUtil.isEmpty(query.getValue())) {
        			sbQuery.append(query.getValue());
                }
        		if (StringUtils.hasText(query.getKey())) {
        			sbQuery.append(query.getKey());
        			if (!ObjectUtil.isEmpty(query.getValue())) {
        				sbQuery.append("=");
        				try {
							sbQuery.append(URLEncoder.encode(ObjectUtil.toString(query.getValue()), "utf-8"));
						} catch (UnsupportedEncodingException e) {
						}
        			}
                }
        	}
        	if (0 < sbQuery.length()) {
        		url += "?" + sbQuery.toString();
        	}
        	request = new HttpGet(url);
		}
		// 判断当前是为post 请求, 即构建post请求.
		else if (method.equals(Method.POST)) {
			request = new HttpPost(url);
			// 判断请求参数列表是否有效, 可用则进行封装请求参数.
			if (null != params && !params.isEmpty()) {
				// 将可用的POST参数列表一个封装成 BasicNameValuePair对象.
				List<NameValuePair> _params = new ArrayList<NameValuePair>();
				for (Entry<String, Object> param : params.entrySet()) {
					Object value = param.getValue();
					if (value instanceof File) {
					} else if (value instanceof InputStream) {
					} else if (value instanceof Byte) {
					} else {
						_params.add(new BasicNameValuePair(param.getKey(), String.valueOf(value)));
					}
				}

				// 判断提供的字符集编码无效, 则使用默认的UTF-8编码.
				charset = (null != charset ? charset : CHARSET);
				// 对POST请求参数列表进行指定编码;
				UrlEncodedFormEntity form = new UrlEncodedFormEntity(_params, charset);
				// 绑定POST表单参数.
				((HttpPost) request).setEntity(form);
			}
		} else {
			return request;
		}

		// 附加多个自定义的请求消息头.
		_addHeader(key, request, headers);
		// 返回HttpRequest 实例.
		return request;
	}

	private static final HttpRequestBase _bulidRequest(String key, Method method, String url, Map<String, String> headers,
			String content, Charset charset) {
		HttpRequestBase request = null;

		// 判断当前是为get 请求, 即构建get请求.
		if (method.equals(Method.GET)) {
			request = new HttpGet(url);
		}
		// 判断当前是为post 请求, 即构建post请求.
		else if (method.equals(Method.POST)) {
			request = new HttpPost(url);
			// 判断请求参数列表是否有效, 可用则进行封装请求参数.
			if (StringUtil.hasText(content)) {
				// 将可用的POST参数列表一个封装成 BasicNameValuePair对象.

				// 判断提供的字符集编码无效, 则使用默认的UTF-8编码.
				charset = (null != charset ? charset : CHARSET);
				// 对POST请求参数列表进行指定编码;
				StringEntity data = new StringEntity(content, charset);
				// 绑定POST表单参数.
				((HttpPost) request).setEntity(data);
			}
		} else {
			return request;
		}

		// 附加多个自定义的请求消息头.
		_addHeader(key, request, headers);

		// 返回HttpRequest 实例.
		return request;
	}

	/**
	 * <pre>
	 * 附加多个自定义的请求消息头.
	 * 
	 * 注：调用者可以覆盖或者添加默认的头信息, 具体以Key<->Value形式, 如果Value为NULL, 则将Key对应的默认头信息移除.
	 * 默认配置的请求信息头如下: 
	 * Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,**;q=0.8
	 * Accept-Encoding:gzip,deflate,sdch
	 * Accept-Language:zh-CN,zh;q=0.8
	 * Connection:keep-alive
	 * Host:request.getURI().getHost()
	 * User-Agent:Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1720.0 Safari/537.36
	 * </pre>
	 * 
	 * @param key
	 *            平台帐号KEY
	 * @param request
	 *            get/post请求对象.
	 * @param headers
	 *            请求信息头.
	 */
	private static final void _addHeader(String key, HttpRequestBase request, Map<String, String> headers) {
		// 设置默认的请求信息头.
		request.setHeader(HttpHeaders.ACCEPT, Header.DEFAULT_ACCEPT_TEXT);
		request.setHeader(HttpHeaders.ACCEPT_LANGUAGE, Header.DEFAULT_ACCEPT_LANGUAGE);
		request.setHeader(HttpHeaders.CACHE_CONTROL, Header.DEFAULT_CACHE_CONTROL);
		request.setHeader(HttpHeaders.USER_AGENT, Header.DEFAULT_USER_AGENT);
		request.setHeader(HttpHeaders.CONNECTION, Header.DEFAULT_CONNECTION);
		request.setHeader(HttpHeaders.HOST, request.getURI().getHost());

		// 判断提供的Header集合有效方可进行添加.
		if (null != headers && !headers.isEmpty()) {
			// 依次将所有的Header设置到请求对象中.
			for (Entry<String, String> entry : headers.entrySet()) {
				_addHeader(request, entry.getKey(), entry.getValue());
			}
		}
		// 添加会话session
		request.setHeader("Cookie", SessionManager.getSessionIds(key));
	}

	/**
	 * <pre>
	 * 附加自定义的请求消息头.
	 * 注：调用者可以覆盖或者添加默认的头信息, 如果headerValue为NULL, 则将headerName对应的默认头信息移除.
	 * </pre>
	 * 
	 * @param request
	 *            请求对象get/post.
	 * @param name
	 *            头信息Name.
	 * @param value
	 *            头信息Value.
	 */
	private static final void _addHeader(HttpRequestBase request, String name, String value) {
		// 判断请求对象和头信息的name都有效.
		if (null != request && StringUtil.hasText(name)) {
			// 判断头信息的value有效, 则进行添加自定义头信息.
			if (StringUtil.hasText(value)) {
				request.removeHeaders(name);
				request.addHeader(name, value);
			}
			// 如果用户设置的属性值为blank, 则代表系统默认设置的Headr将被移除.
			else {
				request.removeHeaders(name);
			}
		}
	}

	/**
	 * 根据提供的Client和Request执行Http访问.
	 * 
	 * @param client
	 * @param request
	 * @param context
	 * @param statusCode
	 * @param contentType
	 * @return HttpResult
	 */
	private static final HttpResult _execute(String key, HttpClient client, HttpRequestBase request, HttpContext context,
			Integer statusCode, String contentType) {
		// 存储HTTP业务处理过程中的异常信息和错误堆栈信息.
		long currTimes = System.currentTimeMillis();
		Integer code = null;
		String error = null;
		Throwable cause = null;
		// 执行HTTP方法调用.
		HttpResponse response = null;
		try {
			response = client.execute(request);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			code = -1001;
			error = "HTTP访问的协议头不匹配.";
			cause = e;
		} catch (ConnectTimeoutException e) {
			code = -1002;
			error = "HTTP连接超时";
			cause = e;
		} catch (SocketTimeoutException e) {
			code = -1003;
			error = "HTTP响应数据读取超时";
			cause = e;
		} catch (UnknownHostException e) {
			code = -1004;
			error = "HTTP未知的HOST";
			cause = e;
		} catch (HttpHostConnectException e) {
			code = -1005;
			error = "HTTP拒绝访问";
			cause = e;
		} catch (NullPointerException e) {
			code = -1006;
			error = "当前HttpClient或Request实例无效";
			cause = e;
		} catch (Throwable e) {
			if (e instanceof OutOfMemoryError) {
				code = -1007;
				error = "HTTP并发、负载过大,内存溢出";
			} else {
				code = -1000;
				error = "HTTP未知异常";
			}
			cause = e;
			e.printStackTrace();
		}

		// 判断需要验证响应状态码是否已提供的校验的状态码一致.
		if (!StringUtil.hasText(error) && null != statusCode) {
			if (null == response.getStatusLine()) {
				code = -1008;
				error = "HTTP响应无效状态码(" + statusCode + ")";
			} else {
				int _statusCode = response.getStatusLine().getStatusCode();
				if (statusCode != _statusCode) {
					code = -1009;
					error = "HTTP响应无效状态码" + _statusCode + "(" + statusCode + ")";
				}
			}
		}

		// 判断是否需要响应的数据类型, 如果需要则判断是否匹配.
		if (ObjectUtil.isEmpty(error) && !ObjectUtil.isEmpty(contentType)) {
			if (!response.containsHeader("Content-Type")) {
				code = -1010;
				error = "HTTP响应无效内容类型(" + contentType + ")";
			} else {
				String _contentType = response.getFirstHeader("Content-Type").getValue();
				if (-1 == _contentType.indexOf(contentType)) {
					code = -1011;
					error = "HTTP响应无效内容类型" + _contentType + "(" + contentType + ")";
				}
			}
		}

		HttpResult result;
		// 最终判断当前是否出现非正常HTTP请求以及响应结果.
		if (!StringUtil.hasText(error)) {
			result = new HttpResult(client, request, response, context, currTimes);
		} else {
			result = new HttpResult(client, request, response, context, code, error, cause, currTimes);
		}

		// 对当前线程持有的历史HttpResult进行相应释放.
		if (null != httpResultHolder.get()) {
			httpResultHolder.get().release();
		}
		// 同时将当前最新的HttpResult实例与该线程进行绑定.
		httpResultHolder.set(result);
		// 保存最新SESSION会话
		SessionManager.updateSession(key, response.getHeaders("Set-Cookie"));
		// 返回运行结果.
		return result;
	}

	/**
	 * 普通增强型执行HTTP请求方法.
	 * 
	 * @param method
	 * @param url
	 * @param headers
	 * @param params
	 * @param context
	 * @param charset
	 * @param statusCode
	 * @param contentType
	 * @param soTimeout
	 * @param connTimeout
	 * @param proxy
	 * @param certPath
	 * @param certPassword
	 * @return HttpResult
	 */
	public static final HttpResult execute(String key, Method method, String url, Map<String, String> headers,
			Map<String, Object> params, HttpContext context, Charset charset, HttpProxy proxy, Integer statusCode,
			String contentType, Integer soTimeout, Integer connTimeout, String certPath, String certPassword) {

		HttpRequestBase request = _bulidRequest(key, method, url, headers, params, charset);
		if (null == request) {
			return new HttpResult(null, null, null, null, null, "create http request fail, request is <null>.", null);
		}

		HttpClient client = _bulidClient(proxy, soTimeout, connTimeout, certPath, certPassword);
		if (null == client) {
			return new HttpResult(client, request, null, null, null, "create http client fail, client is <null>.", null);
		}

		return _execute(key, client, request, context, statusCode, contentType);
	}

	public static final HttpResult execute(String key, Method method, String url, Map<String, String> headers, String content,
			HttpContext context, Charset charset, HttpProxy proxy, Integer statusCode, String contentType, Integer soTimeout,
			Integer connTimeout) {

		HttpRequestBase request = _bulidRequest(key, method, url, headers, content, charset);
		if (null == request) {
			return new HttpResult(null, null, null, null, null, "create http request fail, request is <null>.", null);
		}

		HttpClient client = _bulidClient(proxy, soTimeout, connTimeout, null, null);
		if (null == client) {
			return new HttpResult(client, request, null, null, null, "create http client fail, client is <null>.", null);
		}

		return _execute(key, client, request, context, statusCode, contentType);
	}

	/**
	 * 执行HTTP-GET请求处理.
	 * 
	 * @param url
	 * @return HttpResult
	 */
	public static final HttpResult doGet(String key, String url) {
		return execute(key, Method.GET, url, null, null, null, null, null, null, null, null, null, null, null);
	}

	/**
	 * 执行HTTP-GET请求处理.
	 * 
	 * @param key
	 * @param url
	 * @param headers
	 * context
	 *  statusCode
	 * contentType
	 * @return HttpResult
	 */
	public static final HttpResult doGet(String key, String url, Map<String, String> headers) {
		return execute(key, Method.GET, url, headers, null, null, null, null, null, null, null, null, null, null);
	}

	/**
	 * 执行HTTP-GET请求处理.
	 * 
	 * @param url
	 * @param headers
	 * @param context
	 * @param statusCode
	 * @param contentType
	 * @return HttpResult
	 */
	public static final HttpResult doGet(String key, String url, Map<String, String> headers, HttpContext context,
			Integer statusCode, String contentType) {
		return execute(key, Method.GET, url, headers, null, context, null, null, statusCode, contentType, null, null, null,
				null);
	}

	/**
	 * 执行HTTP-GET请求处理.
	 * 
	 * @param url
	 * @param headers
	 * @param context
	 * @param soTimeout
	 * @param connTimeout
	 * @return HttpResult
	 */
	public static final HttpResult doGet(String key, String url, Map<String, String> headers, HttpContext context,
			Integer soTimeout, Integer connTimeout) {
		return execute(key, Method.GET, url, headers, null, context, null, null, null, null, soTimeout, connTimeout, null, null);
	}

	/**
	 * 执行HTTP-GET请求处理.
	 * 
	 * @param url
	 * @param headers
	 *  context
	 *  soTimeout
	 *  connTimeout
	 * @return HttpResult
	 */
	public static final HttpResult doGet(String key, String url, Map<String, String> headers, Map<String, Object> params) {
		return execute(key, Method.GET, url, headers, params, null, null, null, null, null, null, null, null, null);
	}

	/**
	 * 执行HTTP-GET请求处理.
	 * 
	 * @param url
	 * @param headers
	 * @param context
	 * @param charset
	 * @param statusCode
	 * @param contentType
	 * @param soTimeout
	 * @param connTimeout
	 * @param proxy
	 * @param certPath
	 * @param certPassword
	 * @return HttpResult
	 */
	public static final HttpResult doGet(String key, String url, Map<String, String> headers, HttpContext context,
			Charset charset, Integer statusCode, String contentType, Integer soTimeout, Integer connTimeout, HttpProxy proxy,
			String certPath, String certPassword) {
		return execute(key, Method.GET, url, headers, null, context, charset, proxy, statusCode, contentType, soTimeout,
				connTimeout, certPath, certPassword);
	}

	/**
	 * 执行HTTP-POST请求处理.
	 * 
	 * @param url
	 * @param params
	 * @return HttpResult
	 */
	public static final HttpResult doPost(String key, String url, Map<String, Object> params) {
		return execute(key, Method.POST, url, null, params, null, null, null, null, null, null, null, null, null);
	}

	/**
	 * 执行HTTP-POST请求处理.
	 * 
	 * @param url
	 * @param headers
	 * @param params
	 * @return HttpResult
	 */
	public static final HttpResult doPost(String key, String url, Map<String, String> headers, Map<String, Object> params) {
		return execute(key, Method.POST, url, headers, params, null, null, null, null, null, null, null, null, null);
	}

	public static final HttpResult doPost(String key, String url, Map<String, String> headers, String content) {
		return execute(key, Method.POST, url, headers, content, null, null, null, null, null, null, null);
	}

	/**
	 * 执行HTTP-POST请求处理.
	 * 
	 * @param url
	 * @param headers
	 * @param params
	 * @param context
	 * @param statusCode
	 * @param contentType
	 * @return HttpResult
	 */
	public static final HttpResult doPost(String key, String url, Map<String, String> headers, Map<String, Object> params,
			HttpContext context, Integer statusCode, String contentType) {
		return execute(key, Method.POST, url, headers, params, context, null, null, statusCode, contentType, null, null, null,
				null);
	}

	/**
	 * 执行HTTP-POST请求处理.
	 * 
	 * @param url
	 * @param headers
	 * @param params
	 * @param context
	 * @param soTimeout
	 * @param connTimeout
	 * @return HttpResult
	 */
	public static final HttpResult doPost(String key, String url, Map<String, String> headers, Map<String, Object> params,
			HttpContext context, Integer soTimeout, Integer connTimeout) {
		return execute(key, Method.POST, url, headers, params, context, null, null, null, null, soTimeout, connTimeout, null,
				null);
	}

	/**
	 * 执行HTTP-POST请求处理.
	 * 
	 * @param url
	 * @param headers
	 * @param params
	 * @param statusCode
	 * @param contentType
	 * @param soTimeout
	 * @param connTimeout
	 * @return
	 */
	public static final HttpResult doPost(String key, String url, Map<String, String> headers, Map<String, Object> params,
			Integer statusCode, String contentType, Integer soTimeout, Integer connTimeout) {
		return execute(key, Method.POST, url, headers, params, null, null, null, statusCode, contentType, soTimeout,
				connTimeout, null, null);
	}

	/**
	 * 执行HTTP-POST请求处理.
	 * 
	 * @param url
	 * @param headers
	 * @param params
	 * @param context
	 * @param charset
	 * @param statusCode
	 * @param contentType
	 * @param soTimeout
	 * @param connTimeout
	 * @param proxy
	 * @param certPath
	 * @param certPassword
	 * @return
	 */
	public static final HttpResult doPost(String key, String url, Map<String, String> headers, Map<String, Object> params,
			HttpContext context, Charset charset, Integer statusCode, String contentType, Integer soTimeout,
			Integer connTimeout, HttpProxy proxy, String certPath, String certPassword) {
		return execute(key, Method.POST, url, headers, params, context, charset, proxy, statusCode, contentType, soTimeout,
				connTimeout, certPath, certPassword);
	}
	
	/**
     * Get Request
     * @return
     * @throws Exception
     */
    public static String doGet(String url) throws Exception {
        URL localURL = new URL(url);
        URLConnection connection = localURL.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
        
        httpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;
        
        if (httpURLConnection.getResponseCode() >= 300) {
            throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
        }
        
        try {
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            
            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }
            
        } finally {
            
            if (reader != null) {
                reader.close();
            }
            
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            
            if (inputStream != null) {
                inputStream.close();
            }
            
        }
        
        return resultBuffer.toString();
    }
    
    
    public static String doPost(String url, String content) throws Exception{
    	URL localURL = new URL(url);
        URLConnection connection = localURL.openConnection();
        HttpURLConnection conn = (HttpURLConnection)connection;
        conn.setRequestMethod("POST");
        
        conn.setRequestProperty("Accept-Charset", "UTF-8");
        conn.setRequestProperty("Content-Type", "text/xml");
        if(ObjectUtil.isNotEmpty(content)){
            conn.setDoOutput(true);
        	OutputStream outputStream = conn.getOutputStream();    
            outputStream.write(content.getBytes("UTF-8"));    
            outputStream.close();
        }
        
        if (conn.getResponseCode() >= 300) {
            throw new Exception("HTTP Request is not success, Response code is " + conn.getResponseCode());
        }
        
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;
        try {
            inputStream = conn.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            
            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }
            
        } finally {
            
            if (reader != null) {
                reader.close();
            }
            
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            
            if (inputStream != null) {
                inputStream.close();
            }
            if(null != conn){
            	conn.connect();
            }
        }
        
        return resultBuffer.toString();
    }
    
    /**
     * <pre>
     * <b>发送JSON数据.</b>
     * @param url
     * @param json
     * @return
     * </per>
     */
    @SuppressWarnings("resource")
	public static String sendJson(String url, String json){
    	DefaultHttpClient client = new DefaultHttpClient(cm);
    	HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");

		StringEntity entity = new StringEntity(json, "utf-8");
		entity.setContentType("application/json");
		entity.setContentEncoding("UTF-8");
		httpPost.setEntity(entity);
		CloseableHttpResponse response = null;
		try {
			response = client.execute(httpPost);
			HttpEntity entity2 = response.getEntity();
			
			// 获取响应字符串
			return EntityUtils.toString(entity2, "UTF-8");
		} catch (Exception e) {
			return null;
		}
    }
    
    /**
	 * get
	 * 
	 * @param host
	 * @param path
	 * @param method
	 * @param headers
	 * @param querys
	 * @return
	 * @throws Exception
	 */
	public static HttpResponse doGet(String host, String path, String method, 
			Map<String, String> headers, 
			Map<String, String> querys)
            throws Exception {    	
    	HttpClient httpClient = wrapClient(host);

    	HttpGet request = new HttpGet(buildUrl(host, path, querys));
        for (Entry<String, String> e : headers.entrySet()) {
        	request.addHeader(e.getKey(), e.getValue());
        }
        
        return httpClient.execute(request);
    }
	
	/**
	 * post form
	 * 
	 * @param host
	 * @param path
	 * @param method
	 * @param headers
	 * @param querys
	 * @param bodys
	 * @return
	 * @throws Exception
	 */
	public static HttpResponse doPost(String host, String path, String method, 
			Map<String, String> headers, 
			Map<String, String> querys, 
			Map<String, String> bodys)
            throws Exception {    	
    	HttpClient httpClient = wrapClient(host);

    	HttpPost request = new HttpPost(buildUrl(host, path, querys));
        for (Entry<String, String> e : headers.entrySet()) {
        	request.addHeader(e.getKey(), e.getValue());
        }

        if (bodys != null) {
            List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();

            for (String key : bodys.keySet()) {
                nameValuePairList.add(new BasicNameValuePair(key, bodys.get(key)));
            }
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, "utf-8");
            formEntity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
            request.setEntity(formEntity);
        }

        return httpClient.execute(request);
    }	
	
	/**
	 * Post String
	 * 
	 * @param host
	 * @param path
	 * @param method
	 * @param headers
	 * @param querys
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public static HttpResponse doPost(String host, String path, String method, 
			Map<String, String> headers, 
			Map<String, String> querys, 
			String body)
            throws Exception {    	
    	HttpClient httpClient = wrapClient(host);

    	HttpPost request = new HttpPost(buildUrl(host, path, querys));
        for (Entry<String, String> e : headers.entrySet()) {
        	request.addHeader(e.getKey(), e.getValue());
        }

        if (StringUtil.hasText(body)) {
        	request.setEntity(new StringEntity(body, "utf-8"));
        }

        return httpClient.execute(request);
    }
	
	/**
	 * Post stream
	 * 
	 * @param host
	 * @param path
	 * @param method
	 * @param headers
	 * @param querys
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public static HttpResponse doPost(String host, String path, String method, 
			Map<String, String> headers, 
			Map<String, String> querys, 
			byte[] body)
            throws Exception {    	
    	HttpClient httpClient = wrapClient(host);

    	HttpPost request = new HttpPost(buildUrl(host, path, querys));
        for (Entry<String, String> e : headers.entrySet()) {
        	request.addHeader(e.getKey(), e.getValue());
        }

        if (body != null) {
        	request.setEntity(new ByteArrayEntity(body));
        }

        return httpClient.execute(request);
    }
	
	/**
	 * Put String
	 * @param host
	 * @param path
	 * @param method
	 * @param headers
	 * @param querys
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public static HttpResponse doPut(String host, String path, String method, 
			Map<String, String> headers, 
			Map<String, String> querys, 
			String body)
            throws Exception {    	
    	HttpClient httpClient = wrapClient(host);

    	HttpPut request = new HttpPut(buildUrl(host, path, querys));
        for (Entry<String, String> e : headers.entrySet()) {
        	request.addHeader(e.getKey(), e.getValue());
        }

        if (StringUtil.hasText(body)) {
        	request.setEntity(new StringEntity(body, "utf-8"));
        }

        return httpClient.execute(request);
    }
	
	/**
	 * Put stream
	 * @param host
	 * @param path
	 * @param method
	 * @param headers
	 * @param querys
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public static HttpResponse doPut(String host, String path, String method, 
			Map<String, String> headers, 
			Map<String, String> querys, 
			byte[] body)
            throws Exception {    	
    	HttpClient httpClient = wrapClient(host);

    	HttpPut request = new HttpPut(buildUrl(host, path, querys));
        for (Entry<String, String> e : headers.entrySet()) {
        	request.addHeader(e.getKey(), e.getValue());
        }

        if (body != null) {
        	request.setEntity(new ByteArrayEntity(body));
        }

        return httpClient.execute(request);
    }
	
	/**
	 * Delete
	 *  
	 * @param host
	 * @param path
	 * @param method
	 * @param headers
	 * @param querys
	 * @return
	 * @throws Exception
	 */
	public static HttpResponse doDelete(String host, String path, String method, 
			Map<String, String> headers, 
			Map<String, String> querys)
            throws Exception {    	
    	HttpClient httpClient = wrapClient(host);

    	HttpDelete request = new HttpDelete(buildUrl(host, path, querys));
        for (Entry<String, String> e : headers.entrySet()) {
        	request.addHeader(e.getKey(), e.getValue());
        }
        
        return httpClient.execute(request);
    }
	
	private static String buildUrl(String host, String path, Map<String, String> querys) throws UnsupportedEncodingException {
    	StringBuilder sbUrl = new StringBuilder();
    	sbUrl.append(host);
    	if (StringUtil.hasText(path)) {
    		sbUrl.append(path);
        }
    	if (null != querys) {
    		StringBuilder sbQuery = new StringBuilder();
        	for (Entry<String, String> query : querys.entrySet()) {
        		if (0 < sbQuery.length()) {
        			sbQuery.append("&");
        		}
        		if (!StringUtils.hasText(query.getKey()) && StringUtils.hasText(query.getValue())) {
        			sbQuery.append(query.getValue());
                }
        		if (StringUtils.hasText(query.getKey())) {
        			sbQuery.append(query.getKey());
        			if (StringUtils.hasText(query.getValue())) {
        				sbQuery.append("=");
        				sbQuery.append(URLEncoder.encode(query.getValue(), "utf-8"));
        			}        			
                }
        	}
        	if (0 < sbQuery.length()) {
        		sbUrl.append("?").append(sbQuery);
        	}
        }
    	
    	return sbUrl.toString();
    }
	
	private static HttpClient wrapClient(String host) {
		HttpClient httpClient = new DefaultHttpClient();
		if (host.startsWith("https://")) {
			sslClient(httpClient);
		}
		
		return httpClient;
	}
	
	private static void sslClient(HttpClient httpClient) {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] xcs, String str) {
                	
                }
                public void checkServerTrusted(X509Certificate[] xcs, String str) {
                	
                }
            };
            ctx.init(null, new TrustManager[] { tm }, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx);
            ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = httpClient.getConnectionManager();
            SchemeRegistry registry = ccm.getSchemeRegistry();
            registry.register(new Scheme("https", 443, ssf));
        } catch (KeyManagementException ex) {
            throw new RuntimeException(ex);
        } catch (NoSuchAlgorithmException ex) {
        	throw new RuntimeException(ex);
        }
    }
	
}
