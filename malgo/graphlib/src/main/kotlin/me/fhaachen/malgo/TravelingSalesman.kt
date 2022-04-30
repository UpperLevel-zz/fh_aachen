package me.fhaachen.malgo

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
//            result.connectVertices(result.getVertices().last.getEdge(result.getVertices().first.getId()))
            return result
        }
    }
}