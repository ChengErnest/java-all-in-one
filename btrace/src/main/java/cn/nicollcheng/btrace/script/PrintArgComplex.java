package cn.nicollcheng.btrace.script;

import cn.nicollcheng.btrace.entity.User;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeClassName;
import com.sun.btrace.annotations.ProbeMethodName;

import java.lang.reflect.Field;

@BTrace
public class PrintArgComplex {

    @OnMethod(
            clazz = "cn.nicollcheng.btrace.controller.BTraceController",
            method = "arg2",
            location = @Location(Kind.ENTRY)
    )
    public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn, User user) {
        //print all fields
        BTraceUtils.print("print all fields: ");
        BTraceUtils.printFields(user);

        //print one field
        Field oneFiled = BTraceUtils.field("com.yzfar.video.test.entity.User", "name");
        BTraceUtils.println("print one field: " + BTraceUtils.get(oneFiled, user));

        BTraceUtils.println("ClassName: " + pcn);
        BTraceUtils.println("MethodName: " + pmn);
        BTraceUtils.println();
    }
}