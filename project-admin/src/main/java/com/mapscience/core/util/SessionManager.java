package com.mapscience.core.util;

import org.apache.http.Header;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * <pre>
 * <b>session会话管理器</b>
 * <b>Description:</b> 
 * 
 * <b>Author:</b> jiangyong@typay.me
 * <b>Date:</b> 2014-12-12 9:07:07
 * <b>Copyright:</b> Copyright &copy;2006-2014 typay.me Co., Ltd. All rights reserved.
 * <b>Changelog:</b> 
 *   ----------------------------------------------------------------------
 *   1.0  2014-12-12 9:07:07    jiangyong@typay.me
 *         new file.
 * </pre>
 */
public class SessionManager {

    private static ConcurrentHashMap<String, ConcurrentHashMap<String, String>> globalSessionMap = new ConcurrentHashMap<String, ConcurrentHashMap<String, String>>();

    public static ConcurrentHashMap<String, Boolean> isLoginMap = new ConcurrentHashMap<String, Boolean>();

    /**
     * 根据accountKey 获取 sessionMap，<br/> 如果对应的sessionKey不存在，则返回null。
     * 
     * @param accountKey 平台:帐号
     * @return
     */
    public static ConcurrentHashMap<String, String> getSessionMap(String accountKey) {
        return globalSessionMap.get(accountKey);
    }

    /**
     * 获取sessionIds
     */
    public static String getSessionIds(String accountKey) {
        Map<String, String> sessionMap = globalSessionMap.get(accountKey);
        return toString(sessionMap);
    }

    /**
     * 将http表头转为String
     * 
     * @param headers
     * @return
     */
    public static String toString(Header[] headers) {
        return toString(toMap(headers));
    }

    /**
     * 将http表头转为Map
     * 
     * @param headers
     * @return
     */
    public static ConcurrentHashMap<String, String> toMap(Header[] headers) {
        ConcurrentHashMap<String, String> sessionMap = new ConcurrentHashMap<String, String>();
        for (Header header : headers) {
            String value = header.getValue();
            value = value.substring(0, value.indexOf(";"));
            String[] arr = value.split("=");
            if (arr.length == 2) {
                sessionMap.put(arr[0], arr[1]);
            }
        }
        return sessionMap;
    }

    /**
     * 追加Session <desc> author charis.peng@gmail.com 此处必须带上";"号结尾,因为 getSessionByMap调用转化成字符串时以";"为区分,cookie中设置的sessionID必须有";"间隔
     * </desc> headers
     * 
     * @param headers
     * @param sessionMap
     */
    public static void addSession(Header[] headers, Map<String, String> sessionMap) {
        if (null != headers && headers.length > 0) {
            Map<String, String> map = new HashMap<String, String>();
            for (Header header : headers) {
                String value = header.getValue();
                int start = value.indexOf("=");
                int end = value.indexOf(";");
                if (end == -1) {
                    end = value.length();
                }
                String key = value.substring(0, start);
                String val = value.substring(start + 1, end);
                map.put(key.trim(), val);
            }
            sessionMap.putAll(map);
        }
    }

    /**
     * 将sessionid转为map
     * 
     * @param sessionIds
     * @return
     */
    public static ConcurrentHashMap<String, String> toMap(String sessionIds) {
        ConcurrentHashMap<String, String> sessionMap = new ConcurrentHashMap<String, String>();
        String[] sessions = sessionIds.split(";");
        for (String session : sessions) {
            String[] s = session.split("=");
            if (2 != s.length) {
                continue;
            }
            sessionMap.put(s[0].trim(), s[1].trim());
        }
        return sessionMap;
    }

    /**
     * 将session转为string
     * 
     * @param sessionMap
     * @return
     */
    public static String toString(Map<String, String> sessionMap) {
        if (null != sessionMap && !sessionMap.isEmpty()) {
            StringBuffer sb = new StringBuffer();
            for (String key : sessionMap.keySet()) {
                sb.append(key);
                sb.append("=");
                sb.append(sessionMap.get(key));
                sb.append(";");
            }
            return sb.substring(0, sb.length() - 1);
        }
        return null;
    }

    /**
     * 更新平台的对应的sessionMap
     * 
     * @param accountKey 平台帐号Key, 默认格式为： Platform.B2B_CZ.getCode() + ":" + username;
     * @param sessionMap 新的会话集合
     */
    public static void updateSessionMap(String accountKey, ConcurrentHashMap<String, String> sessionMap) {
        globalSessionMap.put(accountKey, sessionMap);
    }

    /**
     * 更新session
     * 
     * @param headers
     * @param sessionMap
     */
    public static void updateSessionMap(Header[] headers, Map<String, String> sessionMap) {
        ConcurrentHashMap<String, String> tMap = toMap(headers);
        sessionMap.putAll(tMap);
    }

    /**
     * 清除平台帐号的对应的sessionMap.
     * 
     * @param accountKey 平台Key, 默认格式为： CompanyType.Taobao.getCode() + "_" + username;
     */
    public static void cleanSessionMap(String accountKey) {
        if (globalSessionMap.containsKey(accountKey)) {
            globalSessionMap.get(accountKey).clear();
        }
    }

    public static boolean isExistSession(String accountKey) {
        Map<String, String> sessionMap = SessionManager.getSessionMap(accountKey);
        return null != sessionMap && !sessionMap.isEmpty();
    }

    /**
     * 更新session会话
     * 
     * @param key
     * @param headers
     */
    public static void updateSession(String key, Header[] headers) {
        ConcurrentHashMap<String, String> sessionMap = getSessionMap(key);
        if (null == sessionMap) {
            sessionMap = new ConcurrentHashMap<String, String>();
        }
        addSession(headers, sessionMap);
        updateSessionMap(key, sessionMap);
    }

}
