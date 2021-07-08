package cn.nicollcheng.zookeeper.lock;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * <b><code>SpelParser</code></b>
 * <p/>
 * Description SPEL解析
 * <p/>
 * <b>Creation Time:</b> 2021/7/7 17:19.
 *
 * @author nicollcheng
 * @since zookeeper-curator 2021
 */
public class SpelParser {
    private SpelParser(){
        throw new RuntimeException("Cannot use constructor");
    }
    /**
     * 解析SpEL表达式
     * @param key SpEL表达式
     * @param method 反射得到的方法
     * @param args 反射得到的方法参数
     * @return 解析后SpEL表达式对应的值
     */
    public static String parseSpEL(String key, Method method, Object[] args) {
        // 创建解析器
        ExpressionParser parser = new SpelExpressionParser();
        // 通过Spring的LocalVariableTableParameterNameDiscoverer获取方法参数名列表
        LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer
                = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
        // 构造上下文
        EvaluationContext context = new StandardEvaluationContext();
        assert parameterNames != null;
        if (args.length == parameterNames.length) {
            for (int i = 0, len = args.length; i < len; i++) {
                // 使用setVariable方法来注册自定义变量
                context.setVariable(parameterNames[i], args[i]);
            }
        }
        return parser.parseExpression(key).getValue(context, String.class);
    }
}
