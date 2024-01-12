package graph;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Krusukal {

    int getMSTWeight(int n, int[][] A) {
        Arrays.sort(A, (x, y) -> x[2] - y[2]);

        UF uf = new UF(n);
        Arrays.stream(A).forEach(a -> uf.union(a[0], a[1], a[2]));
        return uf.weight;
    }

    int minCostConnectPoints(int[][] points) {
        int n = points.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[0] - y[0]);

        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                pq.add(new int[] {dist(points[i], points[j]), i, j});

        UF uf = new UF(n);
        while (!pq.isEmpty()) {
            int[] next = pq.poll();
            uf.union(next[1], next[2], next[0]);
        }

        return uf.weight;
    }

    int dist(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    class UF {
        int[] parent, size;
        int maxSize = 1;

        int weight = 0;

        UF (int n) {
            this.parent = new int[n];
            this.size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        boolean union(int x, int y, int w) {
            String s = "";
            x = find(x);
            y = find(y);

            if (x == y)
                return false;

            if (size[x] < size[y]) {
                int t = x;
                x = y;
                y = t;
            }
            parent[y] = x;
            size[x] += size[y];
            maxSize = Math.max(maxSize, size[x]);
            weight += w;
            return true;
        }

        int find(int a) {
            if (a != parent[a])
                parent[a] = find(parent[a]);
            return parent[a];
        }
    }
}
