package me.fhaachen.malgo

import java.util.*

class TravelingSalesman {

    companion object {
        fun nearestNeighbor(graph: Graph): Cycle {
            val hc = HamiltonianCycle()
            val vertices = graph.getVertices()
            val visited = BooleanArray(vertices.size)
            var currentVertex = vertices.poll()
            while (!visited[currentVertex.getId()]) {
                visited[currentVertex.getId()] = true
                for (outgoingEdge in currentVertex.outgoingEdges) {
                    if (!visited[outgoingEdge.target.getId()]) {
                        hc.connectVertices(outgoingEdge)
                        currentVertex = outgoingEdge.target
                        break
                    }
                }
            }
            return hc
        }

        fun doppelterBaum(graph: Graph): Graph {
            val mst = MinimumSpanningTree.prim(graph)
            val depthFirstSearch = RelatedComponentCalculator.depthFirstSearch(mst)
            val result = depthFirstSearch.first()
            return translateToGraph(result)
        }

        private fun translateToGraph(searchTree: LinkedList<Vertex>): Graph {
            val result = HamiltonianCycle()
            var previousVertex = searchTree.pollFirst()
            for (currentVertex in searchTree) {
                val edge = previousVertex.getEdge(currentVertex.getId())
                edge.let { it?.let { it1 -> result.connectVertices(it1) } }
                previousVertex = currentVertex
            }
            return result
        }
    }
}