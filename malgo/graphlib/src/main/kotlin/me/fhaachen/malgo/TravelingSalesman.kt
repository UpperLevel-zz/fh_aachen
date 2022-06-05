package me.fhaachen.malgo

import java.util.*

class TravelingSalesman {

    companion object {
        fun nearestNeighbor(graph: Graph, startId: Int): Graph {
            val hc = HamiltonianCycle()
            val visited = BooleanArray(graph.getVertexCount())
            var currentVertex = startId
            var amount: Double
            var lowestEdge: Edge?
            while (!visited[currentVertex]) {
                lowestEdge = null
                visited[currentVertex] = true
                amount = Double.MAX_VALUE
                for (outgoingEdge in graph.getVertex(currentVertex).outgoingEdges) {
                    if (!visited[outgoingEdge.target.getId()] && outgoingEdge.capacity < amount) {
                        lowestEdge = outgoingEdge
                        amount = lowestEdge.capacity
                    }
                }
                if (lowestEdge != null) {
                    hc.connectVertices(lowestEdge)
                    currentVertex = lowestEdge.target.getId()
                }
            }
            hc.connectVertices(graph.getVertex(currentVertex).getEdge(startId))
            return hc
        }

        fun doppelterBaum(graph: Graph, startId: Int): Graph {
            val mst = MinimumSpanningTree.prim(graph, startId)
            val visitedIds = BooleanArray(graph.getVertexCount())
            val depthFirstSearch = RelatedComponentCalculator.depthFirstSearch(mst, startId, visitedIds)
            val distinctOrderedVertices = distinct(depthFirstSearch)
            return hamiltonianCycle(distinctOrderedVertices, graph)
        }

        fun bruteForce(graph: Graph): Graph {
            var result = HamiltonianCycle()
            val vertices = graph.getVertices()
            val permutations = mutableListOf<List<Int>>()
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
            distinctOrderedVertices: List<Int>,
            graph: Graph
        ): HamiltonianCycle {
            var previousVertex = distinctOrderedVertices.first()
            val result = HamiltonianCycle()
            for (currentVertex in distinctOrderedVertices) {
                if (currentVertex != previousVertex) {
                    result.connectVertices(graph.getVertex(previousVertex).getEdge(currentVertex))
                }
                previousVertex = currentVertex
            }
            return result
        }

        private fun distinct(searchTree: List<Int>): LinkedList<Int> {
            val result = LinkedList<Int>()
            val visited = BooleanArray(searchTree.size)
            for (currentVertex in searchTree) {
                if (!visited[currentVertex]) {
                    result.add(currentVertex)
                }
                visited[currentVertex] = true
            }
            result.add(searchTree.first())
            return result
        }
    }

}