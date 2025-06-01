import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class LRUCache {
    // DLinkList + map

    // lock
    private final ReentrantLock lock = new ReentrantLock();

    class DLinkList {
        // key
        public int key;
        public int value;
        public DLinkList next;
        public DLinkList prev;
        public DLinkList(int key, int value) {
            this.key = key;
            this.value = value;
        }
        public DLinkList() {}
    }

    private DLinkList head;
    private DLinkList tail;
    private int capacity;
    private Map<Integer, DLinkList> map = new HashMap<>();
    private int size;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new DLinkList();
        tail = new DLinkList();
        head.next = tail;
        tail.prev = head;
        this.size = 0;
    }

    public int get(int key) {
        lock.lock();
        DLinkList node = map.get(key);
        if (node == null) {
            lock.unlock();
            return -1;
        }
        // 更新lru
        moveNodeToHead(node);

        lock.unlock();
        return node.value;
    }

    // 缓存淘汰策略
    public void put(int key, int value) {
        // 不存在，需要新加入结点
        lock.lock();
        DLinkList node = map.get(key);
        if (node == null) {
            // 添加结点
            node = new DLinkList(key, value);
            map.put(key, node);
            insertToHead(node);
            size += 1;
        } else {
            // 存在， 需要更新结点
            moveNodeToHead(node);
            node.value = value;
        }
        // 驱逐策略
        while (size > 0 && size > this.capacity) {
            int k = removeTail();
            map.remove(k);
            size -= 1;
        }

        lock.unlock();
    }

    // 将结点插入到head中
    private void insertToHead(DLinkList node) {
        if (head.next == node) {
            return;
        }
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    private void moveNodeToHead(DLinkList node) {
        if (node.prev == head) {
            return;
        }
        // 处理node的前后结点
        node.prev.next = node.next;
        node.next.prev = node.prev;
        // 处理head的前后结点
        head.next.prev = node;
        node.next = head.next;
        head.next = node;
        node.prev = head;
    }

    private int removeTail() {
        DLinkList lastNode = tail.prev;
        lastNode.prev.next = tail;
        tail.prev = lastNode.prev;
        return lastNode.key;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // 1
        cache.put(3, 3); // 淘汰 2
        System.out.println(cache.get(2)); // -1
        cache.put(4, 4); // 淘汰 1
        System.out.println(cache.get(1)); // -1
        System.out.println(cache.get(3)); // 3
        System.out.println(cache.get(4)); // 4
    }
}
