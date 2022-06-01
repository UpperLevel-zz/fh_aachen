package me.fhaachen.malgo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MaxFlow {

    public static double fordFulkerson(Graph graph, int source, int target) {
        int u, v;

        var vertexCount = graph.getVertexCount();

        double[][] originalAdjacence = graph.toAdjacentCapacities();
        double[][] residualGraph = Arrays.copyOf(originalAdjacence, originalAdjacence.length);

        int[] path = new int[vertexCount];

        double maximumFlow = 0;

        while (breathFirstSearch(residualGraph, source, target, path)) {
            double pathFlow = Double.MAX_VALUE;
            for (v = target; v != source; v = path[v]) {
                u = path[v];
                pathFlow = Math.min(pathFlow, residualGraph[u][v]);
            }

            for (v = target; v != source; v = path[v]) {
                u = path[v];
                residualGraph[u][v] -= pathFlow;
                residualGraph[v][u] += pathFlow;
            }

            maximumFlow += pathFlow;
        }

        return maximumFlow;
    }

    static boolean breathFirstSearch(double[][] residualGraph, int source, int target, int[] path) {
        boolean[] visited = new boolean[residualGraph.length];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        path[source] = -1;

        while (!queue.isEmpty()) {
            int currentIndex = queue.poll();

            for (int id = 0; id < visited.length; id++) {
                if (id == target) {
                    path[id] = currentIndex;
                    return true;
                }
                if (!visited[id] && residualGraph[currentIndex][id] > 0) {
                    queue.add(id);
                    path[id] = currentIndex;
                    visited[id] = true;
                }
            }
        }
        return false;
    }
}
