package me.fhaachen.malgo

class BellmanFord {

    fun BellmanFord(graph: Graph, residualGraph: Array<DoubleArray>, startId: Int): DoubleArray {
        val V = residualGraph.size
        val dist = DoubleArray(V)

        // Step 1: fill the distance array and predecessor array
        for (i in 0 until V) dist[i] = Double.POSITIVE_INFINITY

        // Mark the source vertex
        dist[startId] = 0.0

        // Step 2: relax edges |V| - 1 times
        val edges = graph.getEdges()
        for (i in 0 until V) {
            for (j in 0 until edges.size) {
                // Get the edge data
                val u = edges[j].source.getId()
                val v = edges[j].target.getId()
                val w = residualGraph[u][v]

                if (dist[u] != Double.POSITIVE_INFINITY && dist[u] + w < dist[v]) dist[v] = dist[u] + w
            }
        }

        // Step 3: detect negative cycle
        // if value changes then we have a negative cycle in the graph
        // and we cannot find the shortest distances
        for (j in 0 until edges.size) {
            val u = edges[j].source.getId()
            val v = edges[j].target.getId()
            val w = residualGraph[u][v]
            if (dist[u] != Double.POSITIVE_INFINITY && dist[u] + w < dist[v]) {
                println("Graph contains negative cycle")
                return dist
            }
        }

        return dist
    }

}