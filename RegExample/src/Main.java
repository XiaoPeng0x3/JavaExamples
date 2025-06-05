// regex
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

    }
    public static void test1() {
        String re = "\\d{3,4}-\\d{7,8}";
        for (String s : List.of("010-12345678", "020-9999999", "0755-7654321")) {
            if (!s.matches(re)) {
                System.out.println("测试失败: " + s);
                return;
            }
        }
        for (String s : List.of("010 12345678", "A20-9999999", "0755-7654.321")) {
            if (s.matches(re)) {
                System.out.println("测试失败: " + s);
                return;
            }
        }
        System.out.println("测试成功!");
    }

    // 测试复杂规则
    public static void test2() {
        // 正则表达式提取子串
    }
}
