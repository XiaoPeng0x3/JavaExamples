public class Person {
    @Range(min = 1, max = 10)
    private String name;
    @Range(min = 18, max = 100)
    public int age;

    public Person() {}
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void sayHello() {
        System.out.println("Hello!");
    }
}