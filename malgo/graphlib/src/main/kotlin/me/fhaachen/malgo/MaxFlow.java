package me.fhaachen.malgo;

// Java program for implementation of Ford Fulkerson
// algorithm

import java.util.LinkedList;

class MaxFlow {

    /* Returns true if there is a path from source 's' to
    sink 't' in residual graph. Also fills path[] to
    store the path */
    static boolean bfs(double[][] rGraph, int s, int t, int[] path) {
        // Create a visited array and mark all vertices as
        // not visited
        boolean[] visited = new boolean[rGraph.length];
        for (int i = 0; i < visited.length; ++i)
            visited[i] = false;

        // Create a queue, enqueue source vertex and mark
        // source vertex as visited
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        path[s] = -1;

        // Standard BFS Loop
        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < visited.length; v++) {
                if (v == t) {
                    path[v] = u;
                    return true;
                }
                if (!visited[v] && rGraph[u][v] > 0) {
                    // If we find a connection to the sink
                    // node, then there is no point in BFS
                    // anymore We just have to set its path
                    // and can return true
                    queue.add(v);
                    path[v] = u;
                    visited[v] = true;
                }
            }
        }

        // We didn't reach sink in BFS starting from source,
        // so return false
        return false;
    }

    // Returns the maximum flow from s to t in the given
    // graph
    public static double fordFulkerson(Graph graph, int s, int t) {
        int u, v;

        // Create a residual graph and fill the residual
        // graph with given capacities in the original graph
        // as residual capacities in residual graph

        // Residual graph where rGraph[i][j] indicates
        // residual capacity of edge from i to j (if there
        // is an edge. If rGraph[i][j] is 0, then there is
        // not)
        var vertexCount = graph.getVertexCount();

        double[][] oGraph = graph.toAdjacentCapacities();
        double[][] rGraph = new double[vertexCount][vertexCount];

        for (u = 0; u < vertexCount; u++)
            for (v = 0; v < vertexCount; v++)
                rGraph[u][v] = oGraph[u][v];

        // This array is filled by BFS and to store path
        int[] path = new int[vertexCount];

        double max_flow = 0; // There is no flow initially

        // Augment the flow while there is path from source
        // to sink
        while (bfs(rGraph, s, t, path)) {
            // Find minimum residual capacity of the edhes
            // along the path filled by BFS. Or we can say
            // find the maximum flow through the path found.
            double path_flow = Double.MAX_VALUE;
            for (v = t; v != s; v = path[v]) {
                u = path[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }

            // update residual capacities of the edges and
            // reverse edges along the path
            for (v = t; v != s; v = path[v]) {
                u = path[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }

            // Add path flow to overall flow
            max_flow += path_flow;
        }

        // Return the overall flow
        return max_flow;
    }
}
