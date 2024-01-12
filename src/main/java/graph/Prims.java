package graph;

import java.util.Arrays;

public class Prims {

    int getMSTWeight(int[][] points) {
        int result  = 0;
        int[] dist = new int[points.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[points.length];
        int cur = 0;
        dist[0] = 0;
        visited[0] = true;
        int mstSize = 1;

        while (mstSize < points.length) {
            int nextMinDist = Integer.MAX_VALUE, next = -1;
            for (int v = 0; v < points.length; v++)
                if (!visited[v]) {
                    dist[v] = Math.min(dist(points[cur], points[v]), dist[v]);
                    if (dist[v] < nextMinDist) {
                        nextMinDist = dist[v];
                        next = v;
                    }
                }

            cur = next;
            visited[cur] = true;
            mstSize++;
            result += nextMinDist;
        }
        return result;
    }

    int dist(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    public static void main(String[] args) {
        System.out.println(
                new Prims().getMSTWeight(new int[][] {{0,0},{2,2},{3,10},{5,2},{7,0}})
        );
    }

}
