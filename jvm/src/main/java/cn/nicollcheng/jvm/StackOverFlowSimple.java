package cn.nicollcheng.jvm;

/**
 * 栈溢出示例
 * 添加启动参数
 *      -Xss128k
 * @author nicollcheng
 * <b>Creation Time:</b> 2021/7/23 11:39.
 */
public class StackOverFlowSimple {

    public static long count=0;

    public static void method(long i){
        System.out.println(count++);
        method(i);
    }

    public static void main(String[] args) {
        method(1);
    }
}
