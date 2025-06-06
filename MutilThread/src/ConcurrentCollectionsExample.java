import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *  使用一些线程安全的数据
 */

public class ConcurrentCollectionsExample {

    public static void main(String[] args) {

    }
    // 并发安全的List
    public static void test1() {

        List<Integer> list = new CopyOnWriteArrayList<>();
        list.add(1);
        list.add(2);
    }

    // 并发安全的Map
    public static void test2() {
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        map.put(1, 1);
        map.put(2, 2);
    }

    // 并发安全的Set
    public static void test3() {
        Set<Integer> set = new CopyOnWriteArraySet<>();
        set.add(1);
        set.add(2);
    }

    // 并发安全的Queue
    public static void test4() {
        Queue<Integer> q = new ConcurrentLinkedDeque<>();
    }
}
