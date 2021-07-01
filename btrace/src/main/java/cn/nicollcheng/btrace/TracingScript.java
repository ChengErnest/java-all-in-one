package cn.nicollcheng.btrace;

import com.sun.btrace.AnyType;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeClassName;
import com.sun.btrace.annotations.ProbeMethodName;

@BTrace
public class TracingScript {
    @OnMethod(
            clazz = "com.yzfar.video.test.controller.BTraceController", method = "arg1",
            location = @Location(Kind.ENTRY)
    )
    public static void anyRead(@ProbeClassName String pcn, // The name of the intercepted class
                               @ProbeMethodName String pmn, // The name of the intercepted method
                               AnyType[] args // The parameter value of the intercepted method
    ) {
        BTraceUtils.println("btrace starting...");
        BTraceUtils.printArray(args);
        BTraceUtils.println(pcn);
        BTraceUtils.println(pmn);
        BTraceUtils.println();
    }
}