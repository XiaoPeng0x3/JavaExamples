import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectExample {

    // 获取类的所有信息
    public static void getInfo() {
        // 获取Person类
        Class<Person> personClass = Person.class;
        // 获取类名
        System.out.println("类名: " + personClass.getName());
        // 获取所有的字段
        for (Field field: personClass.getDeclaredFields()) {
            System.out.println(field.getName() + " " + field.getType());
        }

        // 获取构造函数
        for (Constructor<?> constructor: personClass.getConstructors()) {
            System.out.println("构造函数：: " + constructor.getName());
        }

        // 获取所有方法
        for (Method method: personClass.getMethods()) {
            System.out.println("方法: " + method.getName() + " " + method.getReturnType());
        }
    }

    public static void getPersonClass() {
        // 通过.class获取
        Class<Person> personClass = Person.class;
        System.out.println("直接获取: " + personClass.getName());
        // 通过 forName获取
        try {
            Class<?> person = Class.forName("Person");
            System.out.println("通过forName获取：" + person.getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // 对象实例获取
        Person person = new Person();
        Class<? extends Person> aClass = person.getClass();
        System.out.println("通过实例获取: " + aClass.getName());

        // 通过类加载器获取


    }


    public static void main(String[] args) {
        // 获取所有信息
        getInfo();
    }
}
