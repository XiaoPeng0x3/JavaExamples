import java.lang.reflect.Field;

public class Main {

    public static void check(Person p) throws IllegalAccessException {
        // 获取p的所有字段
        Field[] fields = p.getClass().getDeclaredFields();
        for (Field f : fields) {
            // 将私有属性值也可以设置为null
            f.setAccessible(true);
            // 判断当前字段是不是通过 Range注解注释
            if (f.isAnnotationPresent(Range.class)) {
                // 获取这个注解
                Range range = f.getAnnotation(Range.class);
                // 得到最大值和最小值
                int min = range.min();
                int max = range.max();
                // 判断当前值的类型
                // 传入p相当于给数据附上了确切的值
                Object o = f.get(p);
                if (o instanceof Integer) {
                    // 检查是否符合规范
                    if (((Integer) o).intValue() < min || ((Integer) o).intValue() > max) {
                        throw new IllegalAccessException("min or max value exceeds range");
                    }
                }
                if (o instanceof String) {
                    if (((String) o).length() < min || ((String) o).length() > max) {
                        throw new IllegalAccessException("min or max value exceeds range");
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        //new persion
        Person p = new Person("张三", 38);
        Person p2 = new Person("111", 20);
        try {
            check(p);
            check(p2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
