package cn.nicollcheng.jvm;

import java.util.concurrent.TimeUnit;

/**
 * 死锁示例
 * @author nicollcheng
 * <b>Creation Time:</b> 2021/7/23 11:39.
 */
public class DeadLockSimple {
    private static DeadLockSimple lock = new DeadLockSimple();
    private static DeadLockSimple key = new DeadLockSimple();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (lock) {
                try {
                    System.out.println(Thread.currentThread().getName() + ": 已获取锁");
                    TimeUnit.SECONDS.sleep(1);
                    synchronized (key){
                        System.out.println(Thread.currentThread().getName() + ": 已获取钥匙");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Tom").start();
        new Thread(()->{
            synchronized (key) {
                try {
                    System.out.println(Thread.currentThread().getName() + ": 已获取钥匙");
                    TimeUnit.SECONDS.sleep(1);
                    synchronized (lock){
                        System.out.println(Thread.currentThread().getName() + ": 已获取锁");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Jack").start();
    }

}
