package compression;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCoding {

    class Node {
        final int freq;
        final char c;

        Node left, right;

        Node(int freq, char c) {
            this.freq = freq;
            this.c = c;
        }

        Node(int freq, Node left, Node right) {
            this.freq = freq;
            this.c = '$';
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "freq=" + freq +
                    ", c=" + c +
                    '}';
        }

        private void printCodes(final StringBuilder code) {
            if (this.left == null && this.right == null)
                System.out.println(this.toString() + "; Code: " + code.toString());
            if (left != null) {
                left.printCodes(code.append('0'));
                code.setLength(code.length() - 1);
            }
            if (right != null) {
                right.printCodes(code.append('1'));
                code.setLength(code.length() - 1);
            }
        }
    }

    public void printCodes(String s) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>((x, y) -> x.freq - y.freq);
        for (Map.Entry<Character, Integer> e : frequency(s).entrySet())
            minHeap.offer(new Node(e.getValue(), e.getKey()));

        Node root = null;
        while (minHeap.size() > 1) {
            Node p = minHeap.poll();
            Node q = minHeap.poll();
            minHeap.offer(root = new Node(p.freq + q.freq, p, q));
        }

        root.printCodes(new StringBuilder());
    }

    private HashMap<Character, Integer> frequency(String s) {
        final HashMap<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < s.length(); ++i)
            freq.put(s.charAt(i), freq.getOrDefault(s.charAt(i), 0) + 1);
        return freq;
    }

    public static void main(String[] args) {
        new HuffmanCoding().printCodes("aaaaaaaaaabbbbbbbbbbbbbbbccccccccccccdddeeeefffffffffffffg");
    }
}
