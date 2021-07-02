package cn.nicollcheng.btrace.script;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;

@BTrace
public class PrintJinfo {
    static {
	    // 打印系统属性
    	BTraceUtils.println("System Properties:");
    	BTraceUtils.printProperties();

		// 打印JVM参数
    	BTraceUtils.println("VM Flags:");
    	BTraceUtils.printVmArguments();

		// 打印环境变量
    	BTraceUtils.println("OS Enviroment:");
    	BTraceUtils.printEnv();

		// 退出脚本
    	BTraceUtils.exit(0);
    }
}