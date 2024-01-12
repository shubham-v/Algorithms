package graph;

import java.util.Arrays;

public class FloydWarshall {
    class Graph {

        int[][] minCost;
        int n;

        public Graph(int n, int[][] E) {
            this.n = n;
            this.minCost = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(minCost[i], (int) 1e9);
                minCost[i][i] = 0;
            }

            for (int[] e : E)
                minCost[e[0]][e[1]] = e[2];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++)
                    System.out.print(minCost[i][j] + " ");
                System.out.println();
            }

            for (int k = 0; k < n; k++)
                for (int i = 0; i < n; i++)
                    for (int j = 0; j < n; j++)
                        minCost[i][j] = Math.min(
                                minCost[i][j],
                                minCost[i][k] + minCost[k][j]);
        }

        public void addEdge(int[] e) {
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    minCost[i][j] = Math.min(
                            minCost[i][j],
                            minCost[i][e[0]] + minCost[e[1]][j] + e[2]
                    );
        }

        public int shortestPath(int a, int b) {
            if (minCost[a][b] == (int) 1e9)
                return -1;
            return minCost[a][b];
        }
    }

/**
 * Your Graph object will be instantiated and called as such:
 * Graph obj = new Graph(n, edges);
 * obj.addEdge(edge);
 * int param_2 = obj.shortestPath(node1,node2);
 */
}
