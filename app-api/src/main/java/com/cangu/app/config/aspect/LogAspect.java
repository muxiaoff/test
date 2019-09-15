package com.cangu.app.config.aspect;

import com.alibaba.fastjson.JSONObject;
import com.cangu.app.aspect.Log;
import com.cangu.app.aspect.LogParams;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @description: aop 统一系统日志记录
 * @author: byw
 * @create: 2019-07-26 13:26
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Value("${log.enableLogType}")
    private String enableLogType;

//    @Autowired
//    private SysLogService sysLogsService;

    public LogAspect() {
        System.out.println("******************************LogAspect********************************");
    }

    @Pointcut("@annotation(com.cangu.app.aspect.Log)")
    public void controllerAspect() {

    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @Before("controllerAspect()")
    public  void doBefore(JoinPoint joinPoint) {
        try {
            Object[] arguments = joinPoint.getArgs();
            String request = "";
            if (arguments.length > 0){
                request = JSONObject.toJSONString(String.valueOf(arguments[0]));
            }
            LogParams logParams = getLogParams(joinPoint);
            String methodName = joinPoint.getSignature().getName();
            log.info("业务描述: " + logParams.getDescription() + "		" + methodName);
            log.info("type = : " + logParams.getLogType() );
            log.info("url = : " + logParams.getUrl());
            log.info("业务参数: " + request);
        }catch (Exception inner) {
            inner.printStackTrace();
            log.error("=============================处理日志信息出错=doBefore=============================");
        }
    }

    /**
     * 后置通知
     * @param joinPoint
     */
    @After("controllerAspect()")//声明最终通知
    public void doAfter(JoinPoint joinPoint) {

    }

    /**
     * 成功通知
     * @param joinPoint
     * @param result
     */
    @AfterReturning(pointcut = "controllerAspect()", returning = "result")//声明后置通知
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        saveLog(joinPoint,result,false,null);
    }

    private void saveLog(JoinPoint joinPoint, Object result, boolean error, Exception e) {
        try {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            String ip = "";
            if (null != requestAttributes) {
                HttpServletRequest request = requestAttributes.getRequest();
                ip = request.getRemoteAddr();
            }

            Object[] arguments = joinPoint.getArgs();
            String request = "";
            if (arguments.length > 0){
                request = JSONObject.toJSONString(String.valueOf(arguments[0]));
            }
            LogParams logParams = getLogParams(joinPoint);
            String methodName = joinPoint.getSignature().getName();

//            SysLog sysLog = new SysLog();
//            sysLog.setIpaddress(ip);
//            sysLog.setQueryCode(request);
//            sysLog.setDescription(logParams.getDescription() + "    " + methodName);
//            sysLog.setUrl(logParams.getUrl());
//            sysLog.setTerminalType(51);
//            sysLog.setType(logParams.getLogType());
//            sysLog.preInsert();
//
            if (error) {
                String response = e + JSONObject.toJSONString(e.getStackTrace());
//                sysLog.setResultCode(response);
//                sysLog.setStatus(2);
                log.error("业务描述: " + logParams.getDescription() + "		" + methodName);
                log.error("type = : " + logParams.getLogType() );
                log.error("url = : " + logParams.getUrl());
                log.error("业务参数: " + request);
                log.error("返回结果: " + response);
            } else {
//                String response = JSONObject.toJSONString(result);
//                sysLog.setResultCode(response);
//                sysLog.setStatus(1);
//                log.info("返回结果: " + response);
            }
//            // 支持的日志类型 或 错误日志 存入数据库
//            if (enableLogType.contains(sysLog.getType() + "") || error) {
//                sysLogsService.add(sysLog);
//            }
        }catch (Exception inner) {
            inner.printStackTrace();
            log.error("=============================处理日志信息出错==============================");
        }
    }

    /**
     * 异常通知
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")//声明例外通知
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        saveLog(joinPoint,null,true,e);
    }

    /**
     * 环绕型通知
     * @param
     * @return
     * @throws Throwable
     */
    @Around("controllerAspect()")//声明环绕通知
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object o = joinPoint.proceed();
        return o;
    }


    private static LogParams getLogParams(JoinPoint joinPoint) {
        LogParams logParams = new LogParams();
        try {
            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            Class targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class[] clazzs = method.getParameterTypes();
                    if (clazzs.length == arguments.length) {
                        logParams.setDescription(method.getAnnotation(Log.class).description());
                        logParams.setLogType(method.getAnnotation(Log.class).logType());
                        logParams.setUrl(method.getAnnotation(Log.class).url());
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return logParams;
    }
}
