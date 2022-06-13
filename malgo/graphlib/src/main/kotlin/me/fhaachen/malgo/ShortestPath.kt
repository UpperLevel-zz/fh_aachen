package me.fhaachen.malgo

import java.util.*

class ShortestPath {
    companion object {
        fun dijkstra(graph: Graph, startId: Int): List<ShortestPathElement> {
            val comlexityValue = graph.getEdgeCount().times(graph.getVertexCount())
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
            queue.add(shortestPathElements[startId])
            var step = 0
            while (queue.isNotEmpty()) {
                val currentVertex = queue.poll()
                visited[currentVertex.vertexId] = true
                val currentDistV = shortestPathElements[currentVertex.vertexId].distance
                if (Double.POSITIVE_INFINITY.equals(currentDistV)) {
                    break
                }
                val vertex = graph.getVertex(currentVertex.vertexId)
                for (outgoingEdge in vertex.outgoingEdges) {
                    val newDistW = currentDistV + outgoingEdge.capacity
                    val targetId = outgoingEdge.target.getId()
                    if (shortestPathElements[targetId].distance > newDistW) {
                        shortestPathElements[targetId].distance = newDistW
                        shortestPathElements[targetId].predecessor = vertex.getId()
                        if (step <= comlexityValue) {
                            queue.add(shortestPathElements[targetId])
                        }
                    }
                }
                step++
            }
            println("Count steps: $step")
            return ArrayList(shortestPathElements)
        }

        fun mooreBellmanFord(graph: Graph, startId: Int): ShortestPathResult {
            val visited = BooleanArray(graph.getVertexCount())
            val shortestPathElements = mutableListOf<ShortestPathElement>()
            for (i in 0 until graph.getVertexCount()) {
                val shortestPathElement = ShortestPathElement(i, Double.POSITIVE_INFINITY, null)
                shortestPathElements.add(shortestPathElement)
            }
            shortestPathElements[startId].distance = 0.0
            shortestPathElements[startId].predecessor = startId
            for (n in 0 until graph.getVertexCount() - 1) {
                compareAndUpdateDistances(graph, shortestPathElements, visited)
            }

            val shortestPathElementsLaststep = ArrayList(shortestPathElements)
            val cycleDetected = compareAndUpdateDistances(graph, shortestPathElementsLaststep, visited)
            var cycle: List<Vertex>? = null
            if (cycleDetected) {
                cycle = extractCycle(graph, shortestPathElementsLaststep)
            }
            return ShortestPathResult(ArrayList(shortestPathElementsLaststep), cycle)
        }

        private fun extractCycle(
            graph: Graph,
            shortestPathElementsLaststep: ArrayList<ShortestPathElement>
        ): List<Vertex> {
            println("Graph contains negative cycle")
            val diGraph = DiGraph()
            for (shortestPathElement in shortestPathElementsLaststep) {
                if (shortestPathElement.predecessor != null) {
                    diGraph.connectVertices(
                        Edge(
                            graph.getVertex(shortestPathElement.predecessor!!),
                            graph.getVertex(shortestPathElement.vertexId),
                            shortestPathElement.distance,
                            0.0
                        )
                    )
                }
            }
            val depthFirstSearch = RelatedComponentCalculator.depthFirstSearch(diGraph)
            println(depthFirstSearch)
            return mutableListOf()
        }

        private fun compareAndUpdateDistances(
            graph: Graph,
            shortestPathElements: MutableList<ShortestPathElement>,
            visited: BooleanArray
        ): Boolean {
            var valueStable = true
            var v: Int
            var w: Int
            for (edge in graph.getEdges()) {
                v = edge.source.getId()
                w = edge.target.getId()
                visited[v] = true
                visited[w] = true
                val newDistV = shortestPathElements[v].distance + edge.capacity
                if (newDistV < shortestPathElements[w].distance) {
                    valueStable = false
                    shortestPathElements[w].distance = newDistV
                    shortestPathElements[w].predecessor = v
                }
            }
            return !valueStable
        }
    }

    class ShortestPathElement(val vertexId: Int, var distance: Double, var predecessor: Int?) {
        override fun toString(): String {
            return "ShortestPathElement(vertexId=$vertexId, distance=$distance, predecessor=$predecessor)"
        }
    }

    class ShortestPathResult(
        var shortestPath: ArrayList<ShortestPathElement>,
        var cycle: List<Vertex>?
    )
}