package com.llh.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLogAspect {

    public static final Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);

    /**
     * 5种通知类型
     * 1.前置通知： 方法执行前切入              @Befor
     * 2.后置通知： 方法正常执行完成后切入       @After
     * 3.环绕通知   方法执行前后切入操作         @Around
     * 4.异常通知   方法发生异常时切入操作       @AfterThrowing
     * 5.方法执行后通知      方法执行完成后切入   @AfterReturning
     */

    /**
     * 定义切入点表达式
     *  1.方法返回值是否有限制
     *  2.切入点方法所在包
     *  3. .. 代表切入点位该包及其子包下的全部方法
     *  4. 类名
     *  5.  *(..)   * 类中的方法名 (..)表示方法中的任何参数
     */
    @Around("execution(* com.llh.service.impl..*.*(..))")
    public void recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable{

        logger.info("====== 开始执行切面方法");

        long startTimeStamp = System.currentTimeMillis();
        joinPoint.proceed();
        long endTimeStamp = System.currentTimeMillis();

        long timeStamp = endTimeStamp - startTimeStamp;
        if (timeStamp > 3000) {
            logger.error("====== 执行结束，耗时：{} 毫秒 ======", timeStamp);
        } else {
            logger.info("====== 执行结束，耗时：{} 毫秒 ======", timeStamp);
        }
    }

    // 返回值  包  类 方法
    @Before("execution(* com.llh.service.impl..StuServiceImpl.*(..) )")
    public void testAspect(JoinPoint joinPoint) {
        logger.info("====== {}   start ======",joinPoint.getSignature().getName());

    }


}
