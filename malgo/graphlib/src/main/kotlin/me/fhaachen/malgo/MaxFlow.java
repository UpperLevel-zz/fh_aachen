package me.fhaachen.malgo;

import java.util.LinkedList;
import java.util.Queue;

public final class MaxFlow {

    private MaxFlow() {
        // Utility class
    }

    public static MaxFlowResult edmondsKarp(Graph graph, int source, int target) {
        DiGraph residualGraph = new DiGraph();
        for (Edge edge : graph.getEdges()) {
            residualGraph.connectVertices(edge.copy());
        }

        int[] shortestPath = new int[residualGraph.getVertexCount()];

        double maximumFlow = 0;

        int u;
        int v;
        while (breathFirstSearch(residualGraph, source, target, shortestPath)) {
            double minResidualCapacity = Double.MAX_VALUE;
            for (v = target; v != source; v = shortestPath[v]) {
                u = shortestPath[v];
                minResidualCapacity = Math.min(minResidualCapacity, residualGraph.getVertex(u).getEdge(v).getCapacity());
            }

            for (v = target; v != source; v = shortestPath[v]) {
                u = shortestPath[v];
                residualGraph.getVertex(u).getEdge(v).addCapacity(-minResidualCapacity);
                if (!residualGraph.getVertex(v).hasOutgoingEdge(u)) {
                    Edge originalEdge = residualGraph.getVertex(u).getEdge(v);
                    residualGraph.connectVertices(new Edge(residualGraph.getVertex(v), residualGraph.getVertex(u), 0.0, -originalEdge.getCost()), true);
                }
                residualGraph.getVertex(v).getEdge(u).addCapacity(minResidualCapacity);
                System.out.println();
            }
            maximumFlow += minResidualCapacity;
        }
        return new MaxFlowResult(maximumFlow, residualGraph);
    }

    static boolean breathFirstSearch(Graph residualGraph, int source, int sink, int[] parent) {
        boolean[] visited = new boolean[residualGraph.getVertexCount()];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {
            int currentIndex = queue.poll();

            for (Edge edge : residualGraph.getVertex(currentIndex).getEdges()) {
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
        DiGraph lastResidualGraph;

        public MaxFlowResult(double maxFlow, DiGraph lastResidualGraph) {
            this.maxFlow = maxFlow;
            this.lastResidualGraph = lastResidualGraph;
        }
    }
}
