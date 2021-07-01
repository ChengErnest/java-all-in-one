package cn.nicollcheng.btrace;

import com.sun.btrace.AnyType;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Duration;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.Return;

import static com.sun.btrace.BTraceUtils.println;

/**
 * <b><code>HttpBtraceSimple</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2021/6/29 16:08.
 *
 * @author nicollcheng
 * @since java-all-in-one 2021
 */
@BTrace
public class HttpBtraceSimple {

    /**
     *
     * @param @Self rtspUrl request param
     * @param @Return map response data
     * @param @Duration time response time
     */
    @OnMethod(clazz = "com.yzfar.video.test.controller.VideoTestController", method = "rtspTest", location = @Location(Kind.RETURN))
    public static void traceExecute(AnyType rtspUrl, @Return Object result, @Duration long time) {
        println("com.yzfar.video.test.controller.VideoTestController#rtspTest() method executed!");
        println("parameter  rtspUrl = " + rtspUrl);
        println("result  map = " + result);
        println("cost  time = " + time);
    }
}