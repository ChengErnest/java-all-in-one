package cn.nicollcheng.zookeeper.lock;

/**
 * <b><code>DistributedLock</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2021/7/7 17:17.
 *
 * @author nicollcheng
 * @since zookeeper-curator 2021
 */

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标记对方法进行加锁
 * created at 2019-07-05 15:15
 * @author lerry
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DistributedLock {
    /**
     * redis锁的key值,可使用SpEL传方法参数
     */
    String value();

    /**
     * 超时时间，默认3秒
     */
    int timeout() default 3;
}
