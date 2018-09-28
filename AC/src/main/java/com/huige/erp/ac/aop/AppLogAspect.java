package com.huige.erp.ac.aop;

import com.alibaba.druid.support.json.JSONUtils;
import com.huige.erp.ac.configuration.AcConfiguration;
import com.huige.erp.ac.pojo.po.TAcUserInfo;
import com.huige.erp.common.aop.AppControllerLog;
import com.huige.erp.common.aop.AppServiceLog;
import com.huige.erp.common.apis.service.TCommonLogService;
import com.huige.erp.common.constants.LogTypes;
import com.huige.erp.common.dto.BaseResponseResult;
import com.huige.erp.common.dto.ResponseResult;
import com.huige.erp.common.exception.AppRedirectException;
import com.huige.erp.common.pojo.po.TCommonLog;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Z.xichao
 * @Create 2018-9-28
 * @Comments AOP记录操作&异常日志-切点类
 */
@Aspect
@Component
public class AppLogAspect {

    @Autowired
    private AcConfiguration acConfiguration;

    private static final Logger logger = LoggerFactory.getLogger(AppLogAspect.class);

    // 队列
    private static BlockingQueue<TCommonLog> queue = new LinkedBlockingQueue<>();

    // 缓存线程池
    private static ExecutorService threadPool = Executors.newFixedThreadPool(3);

    // 任务数
    private static int taskSize = 6;

    // 线程是否已启动
    boolean isStartThread = false;

    // 用来启动或停止线程
    static boolean run = true;

    @Autowired
    private TCommonLogService tCommonLogService;


    // Service层切点
    @Pointcut("@annotation(com.huige.erp.common.aop.AppServiceLog)")
    public void serviceAspect() {
    }

    // Controller层切点
    @Pointcut("@annotation(com.huige.erp.common.aop.AppControllerLog)")
    public void controllerAspect() {
    }

    public static BlockingQueue<TCommonLog> getQueue() {
        return queue;
    }

    public static void setQueue(BlockingQueue<TCommonLog> queue) {
        AppLogAspect.queue = queue;
    }

    public static boolean isRun() {
        return run;
    }

    public static void setRun(boolean run) {
        AppLogAspect.run = run;
    }

    /**
     * 返回通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     * @param result    返回值
     * @see [类、类#方法、类#成员]
     */
    @AfterReturning(value = "controllerAspect()", returning = "result")
    public void afterReturn(JoinPoint joinPoint, Object result) {

        TAcUserInfo userInfo = (TAcUserInfo) SecurityUtils.getSubject().getPrincipal();
        if (userInfo == null) throw new AppRedirectException("获取个人信息失败，请重新登录", acConfiguration.getLoginEndpoint());

        String params = "";
        ResponseResult responseResult = new ResponseResult();
        try {
            if (WebResult.class.isInstance(result)) {
                responseResult = (ResponseResult) result;
            }

            InnnerBean innnerBean = getControllerMethodDescription(joinPoint);
            String remark = innnerBean.getDescription();
            String ip = "";
            String reqSource = "";

            Object args[] = joinPoint.getArgs();//n个切点对象

            for (Object arg : args) {
                if (arg instanceof HttpServletRequest) {
                    HttpServletRequest request = (HttpServletRequest) arg;
                    HttpSession session = request.getSession();
                    if (session == null) {
                        return;
                    }
                    ip = request.getHeader("x-forwarded-for");
                    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                        ip = request.getHeader("Proxy-Client-IP");
                    }
                    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                        ip = request.getHeader("WL-Proxy-Client-IP");
                    }
                    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                        ip = request.getRemoteAddr();
                    }

                    String userAgent = request.getHeader("User-Agent");
                    if (userAgent.contains("Android") || userAgent.contains("Linux") || userAgent.contains("iPhone") || userAgent.contains("iPad")) {
                        reqSource = "WEB";
                        if (userAgent.contains("MicroMessenger")) {
                            System.out.println("微信");
                        }
                    } else if (userAgent.contains("Windows")) {
                        reqSource = "PC";
                    }
                } else {
                    params += JSONUtils.toJSONString(arg) + ";";
                }
            }

            TCommonLog log = new TCommonLog.Builder().type(LogTypes.type.operate)
                    .ip(ip)
                    .reqSource(reqSource)
                    .moduleType(innnerBean.getModuleType())
                    .operateCode(joinPoint.getSignature().getName())
                    .operateValue(innnerBean.getOperateValue())
                    .remark(remark)
                    .operateStatus(responseResult.getCode() == BaseResponseResult.SUCCESS ? LogTypes.operateStatus.Y
                            : LogTypes.operateStatus.N)// 返回值1操作成功，否则失败
                    .method((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"))
                    .param(params)
                    .userAccount(userInfo.getUserAccount())
                    .userName(userInfo.getUserName())
                    .build();
            // 放入队列
            queue.put(log);
            if (!isStartThread) {
                for (int i = 0; i < taskSize; i++) {
                    threadPool.execute(new saveLogThread());
                }
                isStartThread = true;
            }
        } catch (Exception e) {
            logger.error("异常信息:{}", e.toString());
        }
    }

    /**
     * 异常通知 用于拦截service层记录异常日志
     *
     * @param joinPoint
     * @param e
     * @see [类、类#方法、类#成员]
     */
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        // 读取session中的用户
        TAcUserInfo userInfo = (TAcUserInfo) SecurityUtils.getSubject().getPrincipal();
        if (userInfo == null) throw new AppRedirectException("获取个人信息失败，请重新登录", acConfiguration.getLoginEndpoint());

        String params = "";

        try {
            if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
                for (int i = 0; i < joinPoint.getArgs().length; i++) {
                    params += joinPoint.getArgs()[i].toString() + ";";
                }
            }

            InnnerBean innnerBean = getServiceMthodDescription(joinPoint);

            TCommonLog log =
                    new TCommonLog.Builder().type(LogTypes.type.exception)
                            .moduleType(innnerBean.getModuleType())
                            .operateCode(joinPoint.getSignature().getName())
                            .operateValue(innnerBean.getOperateValue())
                            .remark(innnerBean.getDescription())
                            .operateStatus(LogTypes.operateStatus.N)
                            .method(
                                    (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"))
                            .param(params)
                            .exceptionDetail(e.toString())
                            .build();
            // 放入队列
            queue.put(log);
            if (!isStartThread) {
                new Thread(new saveLogThread()).start();
                isStartThread = true;
            }
        } catch (Exception ex) {
            logger.error("异常信息:{}", ex.toString());
        } finally {
            logger.error("异常方法:{" + joinPoint.getTarget().getClass().getName() + "}异常代码:{"
                    + joinPoint.getSignature().getName() + "}异常信息:{" + e.toString() + "}参数:{" + params + "}");
        }

    }

    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("rawtypes")
    public static InnnerBean getServiceMthodDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String moduleType = "";
        String operateValue = "";
        String description = "";
        InnnerBean innnerBean = new InnnerBean(moduleType, operateValue, description);
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    AppServiceLog annotation = method.getAnnotation(AppServiceLog.class);
                    moduleType = annotation.moduleType();
                    operateValue = annotation.operateValue();
                    description = annotation.description();
                    innnerBean = new InnnerBean(moduleType, operateValue, description);
                    break;
                }
            }
        }
        innnerBean.setArguments(arguments);
        return innnerBean;
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("rawtypes")
    public static InnnerBean getControllerMethodDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String moduleType = "";
        String operateValue = "";
        String description = "";
        boolean firstParamName = false;
        InnnerBean innnerBean = new InnnerBean(moduleType, operateValue, description);
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    AppControllerLog annotation = method.getAnnotation(AppControllerLog.class);
                    moduleType = annotation.moduleType();
                    operateValue = annotation.operateValue();
                    description = annotation.description();
                    firstParamName = annotation.firstParamName();
                    innnerBean = new InnnerBean(moduleType, operateValue, description);
                    innnerBean.setFirstParamName(firstParamName);
                    break;
                }
            }
        }
        innnerBean.setArguments(arguments);
        return innnerBean;
    }

    /**
     * 内部类封装注入信息
     *
     * @see [相关类/方法]
     * @since [产品/模块版本]
     */
    static class InnnerBean {
        private String moduleType;// 模块代码

        private String description;// 描述

        private String operateValue;// 操作类型

        private boolean firstParamName;

        private Object[] arguments;

        public InnnerBean(String moduleType, String operateValue, String description) {
            super();
            this.moduleType = moduleType;
            this.description = description;
            this.operateValue = operateValue;
        }

        public String getOperateValue() {
            return operateValue;
        }

        public void setOperateValue(String operateValue) {
            this.operateValue = operateValue;
        }

        public String getModuleType() {
            return moduleType;
        }

        public void setModuleType(String moduleType) {
            this.moduleType = moduleType;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Object[] getArguments() {
            return arguments;
        }

        public void setArguments(Object[] arguments) {
            this.arguments = arguments;
        }

        public boolean isFirstParamName() {
            return firstParamName;
        }

        public void setFirstParamName(boolean firstParamName) {
            this.firstParamName = firstParamName;
        }
    }

    /**
     * 异步保存日志
     *
     * @see [相关类/方法]
     * @since [产品/模块版本]
     */
    class saveLogThread implements Runnable {
        Lock lock = new ReentrantLock();

        @Override
        public void run() {
            try {
                while (run) {
                    while (queue.size() != 0) {
                        // 如果对插入顺序无要求，此处不需要同步可提升效率
                        lock.lock();
                        TCommonLog log = queue.take();
                        tCommonLogService.insert(log);
                        lock.unlock();
                    }
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                logger.error("saveLogThread被唤醒：" + e.toString());
            } catch (Exception e) {
                logger.error("saveLogThread异常：" + e.toString());
            }
        }
    }
}
