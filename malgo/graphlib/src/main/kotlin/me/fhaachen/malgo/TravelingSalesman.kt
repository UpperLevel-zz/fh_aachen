package me.fhaachen.malgo

import java.util.*

class TravelingSalesman {

    companion object {
        fun nearestNeighbor(graph: Graph, startVertex: Vertex): Graph {
            val hc = HamiltonianCycle()
            val visited = BooleanArray(graph.getVertexCount())
            var currentVertex = startVertex
            var amount: Double
            var lowestEdge: Edge?
            while (!visited[currentVertex.getId()]) {
                lowestEdge = null
                visited[currentVertex.getId()] = true
                amount = Double.MAX_VALUE
                for (outgoingEdge in currentVertex.outgoingEdges) {
                    if (!visited[outgoingEdge.target.getId()] && outgoingEdge.capacity!! < amount) {
                        lowestEdge = outgoingEdge
                        amount = lowestEdge.capacity!!
                    }
                }
                if (lowestEdge != null) {
                    hc.connectVertices(lowestEdge)
                    currentVertex = lowestEdge.target
                }
            }
            hc.connectVertices(currentVertex.getEdge(startVertex.getId()))
            return hc
        }

        fun doppelterBaum(graph: Graph, startVertex: Vertex): Graph {
            val mst = MinimumSpanningTree.prim(graph, startVertex)
            val visitedIds = BooleanArray(graph.getVertexCount())
            val depthFirstSearch = RelatedComponentCalculator.depthFirstSearch(mst, startVertex.getId(), visitedIds)
            val distinctOrderedVertices = distinct(depthFirstSearch)
            return hamiltonianCycle(distinctOrderedVertices, graph)
        }

        fun bruteForce(graph: Graph): Graph {
            var result = HamiltonianCycle()
            val vertices = graph.getVertices()
            val permutations = mutableListOf<List<Vertex>>()
            Permutation.heapsAlgorithmRekursive(vertices, vertices.size, permutations)
            var amount: Double
            var lowestAmount = Double.MAX_VALUE
            for (permutation in permutations) {
                val vertexList = ArrayList(permutation)
                vertexList.add(
                    permutation.first()
                )
                val hamiltonianCycle = hamiltonianCycle(vertexList, graph)
                amount = hamiltonianCycle.capacity()
                if (lowestAmount > amount) {
                    lowestAmount = amount
                    result = hamiltonianCycle
                }
            }
            return result
        }

        private fun hamiltonianCycle(
            distinctOrderedVertices: List<Vertex>,
            graph: Graph
        ): HamiltonianCycle {
            var previousVertex = distinctOrderedVertices.first()
            val result = HamiltonianCycle()
            for (currentVertex in distinctOrderedVertices) {
                if (currentVertex != previousVertex) {
                    result.connectVertices(graph.getVertex(previousVertex.getId()).getEdge(currentVertex.getId()))
                }
                previousVertex = currentVertex
            }
            return result
        }

        private fun distinct(searchTree: List<Vertex>): LinkedList<Vertex> {
            val result = LinkedList<Vertex>()
            val visited = BooleanArray(searchTree.size)
            for (currentVertex in searchTree) {
                if (!visited[currentVertex.getId()]) {
                    result.add(currentVertex)
                }
                visited[currentVertex.getId()] = true
            }
            result.add(searchTree.first())
            return result
        }
    }

}