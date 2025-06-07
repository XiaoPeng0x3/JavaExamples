import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;


/**
 * 有关stream流的使用
 */


public class Main {


    public static void main(String[] args) {

        // 使用静态方法创建一个stream
        test1();

        // 数组或者集合的方式去创建一个Stream
        test2();

        // map转换
        test4();

        // 字符串转日期
        test5();

        // filter
        test6();

        test7();

        // map reduce
        test8();
    }

    // Stream of 方法
    public static void test1() {
        Stream<String> stringStream = Stream.of("A", "B", "C", "D", "E", "F", "G", "H");

        // forEach
        stringStream.forEach(System.out::println);
    }

    // 基于集合或者数组
    public static void test2() {
        // 基于数组的方式
        Stream<String> stringStream = Arrays.stream(new String[]{"A", "B", "C", "D", "E", "F", "G", "H"});
        // 基于集合的方式
        Stream<String> stringStream1 = List.of("A", "B", "C", "D", "E", "F", "G", "H").stream();
    }

    // 基于Supplier方式去创建
    public static void test3() {

    }

    // map映射操作
    public static void test4() {
        // 创建一个Stream
        System.out.println("Test4--------------------------");
        Stream.of(" A ", "B ", " C", "D ", "E ", "     F", "   G", "H   ")
                .map(String::toUpperCase)
                .map(String::trim)
                .forEach(System.out::println);
    }

    // 字符串转换为日期形式数据
    public static void test5() {
        String[] array = new String[] { " 2019-12-31 ", "2020 - 01-09 ", "2020- 05 - 01 ", "2022 - 02 - 01",
                " 2025-01 -01" };
        // 请使用map把String[]转换为LocalDate并打印:
        System.out.println("Test5--------------------------");
        Arrays.stream(array)
                .map((s) -> s.replaceAll("\\s+", ""))
                .map(LocalDate::parse)
                .forEach(System.out::println);
    }

    // filter过滤
    public static void test6() {
        System.out.println("Test6--------------------------");
        // 过滤一些不合理的数据
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .filter(n -> n % 2 == 0)
                .forEach(System.out::println);
    }

    // 过滤成绩
    public static void test7() {
        System.out.println("Test7-------------------------");
        List<Person> persons = List.of(new Person("小明", 88), new Person("小黑", 62), new Person("小白", 45),
                new Person("小黄", 78), new Person("小红", 99), new Person("小林", 58));
        // 请使用filter过滤出及格的同学，然后打印名字:
        persons.stream()
                .filter(p -> p.score >= 60)
                .sorted((p1, p2) -> (p2.score - p1.score))
                .forEach(p -> System.out.println(p.name));

        // 找出最大分
        System.out.println("-----------------------------------------------");
        persons.stream()
                .max((p1, p2) -> (p1.score - p2.score))
                .ifPresent(p -> System.out.println(p.name));

        // 找出最低分
        persons.stream()
                .filter(p -> p.score >= 60)
                .min((p1, p2) -> (p1.score - p2.score))
                .ifPresent(p -> System.out.println(p.name));
    }

    // 一个map reduce
    public static void test8() {
        System.out.println("Test8----------------------------");
        List<String> props = List.of("profile=native", "debug=true", "logging=warn", "interval=500");

        var map = props.stream()
                .map(
                        kv -> {
                            // 根据“="将字符串分割开
                            String[] split = kv.split("=", 2);
                            return Map.of(split[0], split[1]);
                        }
                )
                .reduce(
                        new HashMap<String, String>(), (m, kv) -> {
                            m.putAll(kv);
                            return m;
                        }
                );

        map.forEach((k, v) -> System.out.println(k + "=" + v));
    }



}

class Person {
    String name;
    int score;

    Person(String name, int score) {
        this.name = name;
        this.score = score;
    }
}