package me.fhaachen.malgo;

import java.util.LinkedList;
import java.util.Queue;

public final class MaxFlow {

    private MaxFlow() {
        // Utility class
    }

    public static MaxFlowResult edmondsKarp(DiGraph graph, int source, int target) {
        int[] shortestPath = new int[graph.getVertexCount()];
        double maximumFlow = 0;

        int u;
        int v;
        while (breathFirstSearch(graph, source, target, shortestPath)) {
            double minResidualCapacity = Double.MAX_VALUE;
            for (v = target; v != source; v = shortestPath[v]) {
                u = shortestPath[v];
                Edge outgoingEdge = graph.getVertex(u).getOutgoingEdge(v);
                minResidualCapacity = Math.min(minResidualCapacity, outgoingEdge.getCapacity());
            }

            for (v = target; v != source; v = shortestPath[v]) {
                u = shortestPath[v];
                Edge originalEdge = graph.getVertex(u).getOutgoingEdge(v);
                var resultingWeight = minResidualCapacity;
                if (originalEdge.getResidual()) {
                    resultingWeight = -minResidualCapacity;
                }
                originalEdge.updateFlow(resultingWeight);
                if (!graph.getVertex(v).hasOutgoingEdge(u)) {
                    graph.createResidualEdge(graph.getVertex(v), graph.getVertex(u), -originalEdge.getCost());
                }
                Edge backwardEdge = graph.getVertex(v).getOutgoingEdge(u);
                backwardEdge.updateFlow(resultingWeight);
            }
            maximumFlow += minResidualCapacity;
        }
        return new MaxFlowResult(maximumFlow);
    }

    static boolean breathFirstSearch(Graph graph, int source, int sink, int[] parent) {
        boolean[] visited = new boolean[graph.getVertexCount()];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {
            int currentIndex = queue.poll();

            for (Edge edge : graph.getVertex(currentIndex).getEdges()) {
                int otherId = edge.getTarget().getId();
                if (!visited[otherId] && edge.getCapacity() > 0) {
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

    static class MaxFlowResult {
        double maxFlow;

        public MaxFlowResult(double maxFlow) {
            this.maxFlow = maxFlow;
        }
    }
}