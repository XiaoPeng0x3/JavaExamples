import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        // 实现动态代理
        UserService login = new UserServiceImpl();

        UserService us = (UserService) Proxy.newProxyInstance(
                login.getClass().getClassLoader(),
                login.getClass().getInterfaces(),
                new LoginInvocationHandler(login)
        );
        us.loginName("张三");
    }
}
