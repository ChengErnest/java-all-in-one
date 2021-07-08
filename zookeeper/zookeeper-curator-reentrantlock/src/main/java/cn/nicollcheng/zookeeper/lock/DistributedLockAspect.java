package cn.nicollcheng.zookeeper.lock;

import cn.nicollcheng.zookeeper.curator.ConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <b><code>DistributedLockAspect</code></b>
 * <p/>
 * Description 分布式锁切面类
 * <p/>
 * <b>Creation Time:</b> 2021/7/7 17:15.
 *
 * @author nicollcheng
 * @since zookeeper-curator 2021
 */
@Slf4j
@Aspect
@Component
public class DistributedLockAspect {

    private final CuratorFramework curatorFramework;
    private final ConfigProperties configProperties;

    @Autowired
    public DistributedLockAspect(CuratorFramework curatorFramework, ConfigProperties configProperties) {
        this.curatorFramework = curatorFramework;
        this.configProperties = configProperties;
    }

    /**
     * 切面配置
     */
    @Pointcut("@annotation(cn.nicollcheng.zookeeper.lock.DistributedLock)" +
            "|| execution(* cn.nicollcheng.zookeeper.controller.*.save*(..))"+
            "|| execution(* cn.nicollcheng.zookeeper.controller.*.update*(..))"+
            "|| execution(* cn.nicollcheng.zookeeper.controller.*.delete*(..))")
    public void distributedLockAspect() {
    }

    @Around(value = "distributedLockAspect()")
    public Object doAround(ProceedingJoinPoint target) throws Throwable {
        // target class
        Class<?> targetClass = target.getTarget().getClass();
        // method name
        String methodName = target.getSignature().getName();
        // parameter types
        Class<?>[] parameterTypes = ((MethodSignature) target.getSignature()).getMethod().getParameterTypes();
        // method
        Method method = targetClass.getMethod(methodName, parameterTypes);
        // arguments
        Object[] arguments = target.getArgs();
        return lock(target, method, arguments);
    }

    private Object lock(ProceedingJoinPoint target, Method method, Object[] arguments) throws Throwable {
        DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);
        String lockName = method.getDeclaringClass().getSimpleName() + "#" + method.getName();
        if (!Objects.isNull(distributedLock)){
            lockName = SpelParser.parseSpEL(distributedLock.value(), method, arguments);
        }
        InterProcessMutex mutex = new InterProcessMutex(curatorFramework, configProperties.getRootLockNode() + lockName);
        long timeout = Objects.isNull(distributedLock) ? configProperties.getLockTimeout() : distributedLock.timeout();
        log.info(lockName + " locking...");
        if (mutex.acquire(timeout, TimeUnit.SECONDS)) {
            try {
                return target.proceed();
            } finally {
                mutex.release();
            }
        }
        return null;
    }

    @AfterThrowing(value = "distributedLockAspect()", throwing = "ex")
    public void afterThrowing(Throwable ex) {
        throw new RuntimeException("Exec method failed!\n" + ex);
    }

}