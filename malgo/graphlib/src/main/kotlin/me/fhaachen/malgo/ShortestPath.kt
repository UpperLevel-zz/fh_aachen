package me.fhaachen.malgo

import java.util.*

class ShortestPath {
    companion object {
        fun dijkstra(graph: Graph, startId: Int): List<ShortestPathElement> {
            val shortestPathElements = mutableListOf<ShortestPathElement>()
            val capacityWeighted: Comparator<ShortestPathElement> = compareBy { it.distance }
            val queue = PriorityQueue(capacityWeighted)
            val visited = BooleanArray(graph.getVertexCount())
            for (i in visited.indices) {
                val shortestPathElement = ShortestPathElement(i, Double.POSITIVE_INFINITY, null)
                shortestPathElements.add(shortestPathElement)
                visited[i] = false
            }
            shortestPathElements[startId].distance = 0.0
            shortestPathElements[startId].predecessor = startId
            visited[startId] = true
            queue.add(shortestPathElements[startId])
            while (queue.isNotEmpty()) {
                val currentVertex = queue.poll()
                val currentDistV = shortestPathElements[currentVertex.vertexId].distance
                if (Double.POSITIVE_INFINITY.equals(currentDistV)) {
                    break
                }
                val vertex = graph.getVertex(currentVertex.vertexId)
                for (adjacentVertex in vertex.getAdjacentVertices()) {
                    val newDistW = currentDistV + vertex.getEdge(adjacentVertex).capacity!!
                    if (shortestPathElements[adjacentVertex].distance > newDistW) {
                        shortestPathElements[adjacentVertex].distance = newDistW
                        shortestPathElements[adjacentVertex].predecessor = vertex.getId()
                        if (!visited[adjacentVertex]) {
                            visited[adjacentVertex] = true
                            queue.add(shortestPathElements[adjacentVertex])
                        }
                    }
                }
            }
            return ArrayList(shortestPathElements)
        }

        class ShortestPathElement(val vertexId: Int, var distance: Double, var predecessor: Int?) {
            override fun toString(): String {
                return "DijkstaElement(vertexId=$vertexId, distance=$distance, predecessor=$predecessor)"
            }
        }

        fun mooreBellmanFord(graph: Graph, startId: Int): List<ShortestPathElement> {
            val shortestPathElements = mutableListOf<ShortestPathElement>()
            for (i in 0 until graph.getVertexCount()) {
                val shortestPathElement = ShortestPathElement(i, Double.POSITIVE_INFINITY, null)
                shortestPathElements.add(shortestPathElement)
            }
            shortestPathElements[startId].distance = 0.0
            shortestPathElements[startId].predecessor = startId
            for (n in 0 until graph.getVertexCount() - 1) {
                compareAndUpdateDistances(graph, shortestPathElements)
            }

            val shortestPathElementsLaststep = ArrayList(shortestPathElements)
            val cycleDetected = compareAndUpdateDistances(graph, shortestPathElementsLaststep)
            if (cycleDetected) {
                extractCycle(graph, shortestPathElementsLaststep)
            }
            return ArrayList(shortestPathElementsLaststep)
        }

        private fun extractCycle(
            graph: Graph,
            shortestPathElementsLaststep: ArrayList<ShortestPathElement>
        ) {
            println("Cycle detected")
            val diGraph = DiGraph(graph.getVertexCount())
            for (shortestPathElement in shortestPathElementsLaststep) {
                if (shortestPathElement.predecessor != null) {
                    diGraph.connectVertices(
                        Edge(
                            graph.getVertex(shortestPathElement.predecessor!!),
                            graph.getVertex(shortestPathElement.vertexId),
                            shortestPathElement.distance
                        )
                    )
                }
            }
            val depthFirstSearch = RelatedComponentCalculator.depthFirstSearch(diGraph)
            println(depthFirstSearch)
        }

        private fun compareAndUpdateDistances(
            graph: Graph,
            shortestPathElements: MutableList<ShortestPathElement>
        ): Boolean {
            var predStable = true
            var valueStable = true
            for (edge in graph.getEdges()) {
                val newDistV = shortestPathElements[edge.source.getId()].distance + edge.capacity!!
                if (newDistV < shortestPathElements[edge.target.getId()].distance) {
                    valueStable = valueStable && (shortestPathElements[edge.target.getId()].distance == newDistV)
                    shortestPathElements[edge.target.getId()].distance = newDistV
                    predStable =
                        predStable && (shortestPathElements[edge.target.getId()].predecessor == edge.source.getId())
                    shortestPathElements[edge.target.getId()].predecessor = edge.source.getId()
                }
            }
            return predStable && !valueStable
        }
    }
}