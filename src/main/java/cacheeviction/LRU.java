package cacheeviction;

import java.util.HashMap;
import java.util.Map;

public class LRU<K, V> {

    private final Node head = new Node(null);
    private final Node tail = new Node(null);
    private final Map<K, Node> nodeMap;
    private final int capacity;

    public LRU(int capacity) {
        this.capacity = capacity;
        this.nodeMap = new HashMap<>(capacity);
        this.head.next = tail;
        this.tail.prev = head;
    }

    public V get(K key) {
        V result = null;
        Node node = this.nodeMap.get(key);
        if (node != null) {
            remove(node);
            add(node);
            result = node.value;
        }
        return result;
    }

    public void put(K key, V value) {
        Node node = this.nodeMap.get(key);
        if (node != null) {
            node.value = value;
            remove(node);
            add(node);
        } else {
            if (this.nodeMap.size() == this.capacity) {
                this.nodeMap.remove(this.tail.prev.key);
                remove(this.tail.prev);
            }
            Node newNode = new Node(key, value);
            add(newNode);
            this.nodeMap.put(key, newNode);
        }
    }

    private void add(Node node) {
        Node headNext = this.head.next;
        this.head.next = node;
        node.prev = this.head;
        node.next = headNext;
        headNext.prev = node;
    }

    private void remove(Node node) {
        Node nextNode = node.next;
        Node prevNode = node.prev;
        nextNode.prev = prevNode;
        prevNode.next = nextNode;
    }

    private class Node {
        private final K key;
        private V value;
        private Node prev;
        private Node next;
        private Node(final K key) {
            this.key = key;
        }
        private Node(final K key, final V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "[" + key + "," + value + "]";
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node temp = head;
        while (temp != null) {
            builder.append(temp.toString()).append("->");
            temp = temp.next;
        }
        builder.append("\n").append(nodeMap);
        return builder.toString();
    }

    public static void main(String[] args) {
        LRU<Integer, Integer> lru = new LRU<>(0);
        lru.put(1, 1);
        lru.put(2, 2);
        System.out.println(lru.get(1));
        lru.put(3, 3);
        System.out.println(lru.get(2));
        lru.put(4, 4);
        System.out.println(lru.get(1));
        System.out.println(lru.get(3));
        System.out.println(lru.get(4));
        System.out.println(lru);
    }

}
