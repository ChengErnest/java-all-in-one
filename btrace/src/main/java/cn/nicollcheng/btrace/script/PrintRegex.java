package cn.nicollcheng.btrace.script;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeClassName;
import com.sun.btrace.annotations.ProbeMethodName;

@BTrace
public class PrintRegex {

    @OnMethod(
		    // 类名也可以使用正则表达式进行匹配
            clazz = "cn.nicollcheng.btrace.controller.BtraceController",
            // 正则表达式需要写在两个斜杠内
            method = "/.*/"  
    )
    public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn) {
        BTraceUtils.println("ClassName: " + pcn);
        BTraceUtils.println("MethodName: " + pmn);
        BTraceUtils.println();
    }
}