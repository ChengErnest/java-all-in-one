package cn.nicollcheng.btrace.script;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeClassName;
import com.sun.btrace.annotations.ProbeMethodName;

@BTrace
public class PrintLine {
	
	@OnMethod(
	        clazz="cn.nicollcheng.btrace.controller.BTraceController",
	        method="exception",
	        location=@Location(value=Kind.LINE, line=43)  // Intercept line 43
	)
	public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn, int line) {
		BTraceUtils.println("ClassName: " + pcn);
		BTraceUtils.println("MethodName: " + pmn);
		BTraceUtils.println("line: " + line);
		BTraceUtils.println();
    }
}