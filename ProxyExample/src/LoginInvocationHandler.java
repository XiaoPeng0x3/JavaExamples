import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoginInvocationHandler implements InvocationHandler {

    private Object target;
    public LoginInvocationHandler(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 日志调用前
        System.out.println("登陆前:.....");
        Object result = method.invoke(target, args);
        System.out.println("登陆后....");
        return result;
    }
}
