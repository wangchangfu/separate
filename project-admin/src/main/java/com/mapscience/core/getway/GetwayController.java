package com.mapscience.core.getway;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.mapscience.core.getway.bean.ValidMessage;
import com.mapscience.core.util.*;
import com.mapscience.core.util.common.StringUtil;
import com.mapscience.core.getway.exception.runtimeException.TypayRuntimeException;
import com.mapscience.modular.system.model.Merchant;
import com.mapscience.modular.system.service.IMerchantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
public class GetwayController {
    protected static Logger logger = null;
    {logger = LoggerFactory.getLogger(this.getClass());}
    public GetwayController() { }


    public Object geteWay(HttpServletRequest request, HttpServletResponse response, Map<String, Object> params, IMerchantService merchantService){
        String paramsJsonStr = JsonUtil.toJson(params);
        // 检验基本参数
        if(ObjectUtil.isEmpty(params)){
            logger.warn("缺少请求方法参数，不能路由网关请求。参数：[" + paramsJsonStr + "]");
            //return new Result(500, "缺少请求方法参数，不能路由网关请求。");
        }
        String paramsHeadStr = ObjectUtil.toString(params.get("head"));
        if(ObjectUtil.isEmpty(paramsHeadStr)){
            logger.warn("请求参数错误。参数：[" + paramsJsonStr + "]");
            //return new Result(500, "请求参数错误。");
        }
        //解密base64
        paramsHeadStr = new String(Base64Utils.decode(paramsHeadStr.getBytes()));
        JSONObject paramsHead = JSONObject.parseObject(paramsHeadStr);
        if(ObjectUtil.isEmpty(paramsHead)){
            logger.warn("请求参数头转换错误。参数：[" + paramsHeadStr + "]");
            //return new Result(500, "请求参数头转换错误。");
        }
        // 获取method和version
        String methodKey = paramsHead.getString("method"), version = ObjectUtil.toString(paramsHead.getString("version"), CommConst.VERSION_1_0_2);
        if(ObjectUtil.isEmpty(methodKey)){
            logger.warn("缺少请求方法参数，不能路由网关请求。参数：[" + paramsHeadStr + "]");
            return new Result(500, "缺少请求方法参数，不能路由网关请求。");
        }
        String gateWayKey = methodKey + "." + version;
        Method method = GateWayConfCache.getWayMethod(gateWayKey);
        if(null == method){
            return new Result(500, "系统未定义，未能路由到执行方法，参数：[" + paramsHeadStr + "]");
        }
        //获取商户Id
        String merchantId = ObjectUtil.toString(paramsHead.get("merchantId"));
        if(ObjectUtil.isEmpty(merchantId)) {
            logger.warn("缺少商户ID。参数：[" + paramsHeadStr + "]");
            return new Result(500, "缺少商户ID。");
        }
        //获取商户信息
        Merchant merchant = merchantService.getMerchant(new Merchant(merchantId));
        if(ObjectUtil.isEmpty(merchant)) {
            logger.warn("没有找到商户ID。参数：[" + paramsHeadStr + "]");
            return new Result(500, "没有找到商户ID。");
        }
        //获取key
        String key = merchant.getMerchantKey();
        //获取加密params
        String bodyStr=  ObjectUtil.toString(params.get("body"));
        if(ObjectUtil.isEmpty(bodyStr)) {
            logger.warn("没有实体。参数：[" + paramsJsonStr + "]");
            return new Result(500, "没有实体。");
        }
        //进行解密
        String body =  AESUtil.decrypt(bodyStr, key);
        if(ObjectUtil.isEmpty(body)) {
            logger.warn("没有实体。参数：[" + paramsJsonStr + "]");
            return new Result(500, "没有实体。");
        }
        // 获取方法参数类型
        Class<?>[] parameterTypes = method.getParameterTypes();
        // 组装传递参数
        Object[] arguments = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> clazzType = parameterTypes[i];
            if(clazzType == HttpServletRequest.class){
                arguments[i] = request;
                continue;
            }
            if(clazzType == HttpServletResponse.class){
                arguments[i] = response;
                continue;
            }
            if(clazzType == BaseWayRequest.class || BaseWayRequest.class.isAssignableFrom(clazzType)){
                arguments[i] = JsonUtil.toBean(body, clazzType);
            }
        }
        logger.info("开始-["+ methodKey + ",版本号:" + version + "], [入参:" + body+ "], [方法："+ method.toString() +"]");
        // 获取方法参数规则
        if(arguments.length > 0){
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            if(ObjectUtil.isNotEmpty(parameterAnnotations)){
                // 保存参数下标的验证规则
                LinkedHashMap<Integer, ParameterRule> rules = Maps.newLinkedHashMap();
                for (int index = 0; index < parameterAnnotations.length; index++) {
                    Annotation[] _annotations = parameterAnnotations[index];
                    // 遍历参数注解,判断是否为自定义验证注解,加入验证规则
                    for (Annotation annotation : _annotations) {
                        if (annotation.annotationType() == ParameterRule.class) {
                            ParameterRule rule = (ParameterRule) annotation;
                            rules.put(index, rule);
                        }
                    }
                }
                // 检查是否需要验证
                List<String> validMessage = this.validParameters(rules, arguments);
                if(ObjectUtil.isNotEmpty(validMessage)){
                    return new Result(500, StringUtil.toDelimitedString(validMessage));
                }
                validMessage = null;
                rules = null;
            }
            parameterAnnotations = null;
        }
        Object result = null;
        try {
            // 获取bean对象
            Object bean = IOCutil.getBean(method.getDeclaringClass());
            result = ReflectUtil.invokeMethod(bean, method.getName(), method.getParameterTypes(), arguments);
        } catch (TypayRuntimeException e) {
            logger.error("WayController.gateway 异常[" + gateWayKey + "]", e);
            result = new Result(e.getMsg().CODE, e.getMessage());
        } catch (InvocationTargetException e) {
            String message = "系统繁忙，请稍后再试。";
            if(e.getTargetException() instanceof TypayRuntimeException){
                message = e.getTargetException().getMessage();
            }
            logger.error("WayController.gateway 异常[" + gateWayKey + "]", e);
            result = new Result(500, message);
        } catch (Exception e) {
            logger.error("WayController.gateway 异常[" + gateWayKey + "]", e);
            result = new Result(500, "系统繁忙，请稍后再试。");
        } finally {
            logger.info("结束-["+ methodKey + ",版本号:" + version +"], [结果:" +JsonUtil.toJson(result) + "], [方法:"+ method.toString() +"]");
            params = null;
        }
        return result;
    }

    /**
     *
     * <pre>
     * <b>通过注解验证参数规则.</b>
     * <b>Description:</b>
     *
     * <b>Author:</b> zhouguangyong@typay.me
     * <b>Date:</b> 2016-4-27 上午10:38:03
     * annotations
     * @param arguments
     * @return
     * </pre>
     */
    private List<String> validParameters(LinkedHashMap<Integer, ParameterRule> rules, Object[] arguments) {
        // 默认验证通过
        List<String> messages = new ArrayList<String>();
        // 获取自定义验证规则
        if (ObjectUtil.isEmpty(rules)) {
            return messages;
        }

        // 遍历参数验证
        for (int index = 0; index < arguments.length; index++) {
            ParameterRule rule = rules.get(index);
            if (null == rule) {
                continue;
            }
            // 获取参数值
            Object value = arguments[index];
            if(value == null){
                messages.add("[参数" + index + "]不能为空");
                continue;
            }

            // 如果参数值是请求对象
            if (value.getClass() == BaseWayRequest.class || BaseWayRequest.class.isAssignableFrom(value.getClass())) {
                // 验证实体注解字段
                ValidMessage valid = this.validBean(value);
                if (!valid.isValid()) {
                    messages.add(valid.getMessage());
                    continue;
                }
            }
            String message = rule.message();

            if (ObjectUtil.getSize(value) < rule.minLength()) {
                messages.add("[参数" + index + "]最小长度为" + rule.minLength());
            }
            if (ObjectUtil.getSize(value) > rule.maxLength()) {
                messages.add("[参数" + index + "]最大长度为" + rule.maxLength());
            }
            if (!ObjectUtil.isEmpty(rule.regEx())
                    && !RegexUtil.isMatch(ObjectUtil.toString(value), Pattern.compile(rule.regEx()))) {
                messages.add("[参数" + index + "]" + (ObjectUtil.isEmpty(message) ? "数据格式不合法": rule.message()));
            }
        }
        return messages;
    }



    /**
     *
     * <pre>
     * <b>验证报文实体.</b>
     * <b>Description:</b>
     *
     * <b>Author:</b> zhouguangyong@typay.me
     * <b>Date:</b> 2016-3-17 上午10:42:56
     * @param bean
     * @return
     * </pre>
     */
    public ValidMessage validBean(Object bean) {
        ValidMessage result = new ValidMessage(true, "");
        if (null == bean) {
            result.setValid(false);
            result.setMessage("缺少必填参数");
            return result;
        }
        // 默认验证通过
        List<String> messages = new ArrayList<String>();
        Field[] fields = this.getAnnotationFields(bean.getClass(), ParameterRule.class);
        // 遍历注解属性
        for (Field field : fields) {
            // 获取注解属性
            ParameterRule validation = field.getAnnotation(ParameterRule.class);
            Object value = ReflectUtil.getValue(bean, field.getName());
            // 验证是否为空
            if (validation.required() && ObjectUtil.isEmpty(value)) {
                messages.add("[" + field.getName() + "]不能为空");
            }

            if (ObjectUtil.getSize(value) < validation.minLength()) {
                messages.add("[" + field.getName() + "]最小长度为" + validation.minLength());
            }
            if (ObjectUtil.getSize(value) > validation.maxLength()) {
                messages.add("[" + field.getName() + "]最大长度为" + validation.maxLength());
            }

            if (!ObjectUtil.isEmpty(validation.regEx())
                    && !RegexUtil.isMatch(ObjectUtil.toString(value), Pattern.compile(validation.regEx()))) {
                messages.add("[" + field.getName() + "]" + (ObjectUtil.isEmpty(validation.message()) ? "数据格式不合法": validation.message()));
            }
        }
        if (!ObjectUtil.isEmpty(messages)) {
            result.setValid(false);
            result.setMessage(StringUtil.toDelimitedString(messages, ", "));
            result.setData(bean);
        }
        return result;
    }


    /**
     *
     * <pre>
     * <b>获取实体注解属性.</b>
     * <b>Description:</b>
     *
     * <b>Author:</b> zhouguangyong@typay.me
     * <b>Date:</b> 2016-3-17 上午11:03:42
     * @param clazz
     * @return
     * </pre>
     */
    protected Field[] getAnnotationFields(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        // 获取类的所有属性
        Field[] fields = clazz.getDeclaredFields();
        List<Field> _fields = new ArrayList<Field>();
        // 遍历获取标有注解的属性
        for (Field field : fields) {
            if (field.isAnnotationPresent(annotationClass)) {
                _fields.add(field);
            }
        }
        return _fields.toArray(new Field[] {});
    }

}
