package me.fhaachen.malgo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MaxFlow {

    public static double edmondsKarp(Graph graph, int source, int target) {
        double[][] residualGraph = Arrays.copyOf(graph.toAdjacentCapacities(), graph.getVertexCount());

        int[] shortestPath = new int[residualGraph.length];

        double maximumFlow = 0;

        int u, v;
        while (breathFirstSearch(residualGraph, source, target, shortestPath)) {
            double minResidualCapacity = Double.MAX_VALUE;
            for (v = target; v != source; v = shortestPath[v]) {
                u = shortestPath[v];
                minResidualCapacity = Math.min(minResidualCapacity, residualGraph[u][v]);
            }

            for (v = target; v != source; v = shortestPath[v]) {
                u = shortestPath[v];
                residualGraph[v][u] += minResidualCapacity;
                residualGraph[u][v] -= minResidualCapacity;
            }

            maximumFlow += minResidualCapacity;
        }

        return maximumFlow;
    }

    static boolean breathFirstSearch(double[][] residualGraph, int source, int sink, int[] parent) {
        boolean[] visited = new boolean[residualGraph.length];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {
            int currentIndex = queue.poll();

            for (int otherId = 0; otherId < visited.length; otherId++) {
                if (!visited[otherId] && residualGraph[currentIndex][otherId] > 0) {
                    if (otherId == sink) {
                        parent[otherId] = currentIndex;
                        return true;
                    }
                    queue.add(otherId);
                    parent[otherId] = currentIndex;
                    visited[otherId] = true;
                }
            }
        }
        return false;
    }
}
