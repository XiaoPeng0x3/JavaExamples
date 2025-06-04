import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayListExample {
    public static void main(String[] args) {
        Person person1 = new Person("张三", 20);
        Person person2 = new Person("李四", 10);
        Person person3 = new Person("王五", 17);
        Person person4 = new Person("赵六", 8);
        Person person5 = new Person("cj", 9);


        // 虽然Integer和Number是继承关系
        // 但是 ArrayList<Number> numberList 和 ArrayList<Integer>不是继承关系
        // ArrayList<Number> numberList = list;
        List<Person> persons = new ArrayList<Person>();
        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        persons.add(person4);
        persons.add(person5);

        Collections.sort(persons, (Person p1, Person p2) -> {
            return p1.age - p2.age;
        });

        System.out.println(persons);

    }
}
class Person implements Comparable<Person> {
    private String name;
    public int age;

    public Person() {}
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void sayHello() {
        System.out.println("Hello!");
    }
    public String toString() {
        return "name:"  + name + " " + "age:" + age;
    }
    // 重写compart接口
    @Override
    public int compareTo(Person o) {
        return o.age - age;
    }
}
