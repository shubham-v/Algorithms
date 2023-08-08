package memoryallocation;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * ToDo
 */
public class Buddy {

    private static class Pair {
        private int lowerBound;
        private int upperBound;
        public Pair(int lowerBound, int upperBound) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }
    }

    private final ArrayList<Pair>[] nodes;

    Buddy(int size) {
        int x = (int) Math.ceil(Math.log(size) / Math.log(2));
        this.nodes = new ArrayList[x+1];
        IntStream.rangeClosed(0, x).forEach(i -> this.nodes[i] = new ArrayList<>());

    }

}
