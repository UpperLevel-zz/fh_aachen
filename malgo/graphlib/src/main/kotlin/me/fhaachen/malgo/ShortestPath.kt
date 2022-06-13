package me.fhaachen.malgo

import java.util.*

class ShortestPath {
    companion object {
        fun dijkstra(graph: Graph, startId: Int): List<ShortestPathElement> {
            val comlexityValue = graph.getEdgeCount().times(graph.getVertexCount())
            val shortestPathElements = mutableListOf<ShortestPathElement>()
            val queue = PriorityQueue<ShortestPathElement>(compareBy { it.distance })
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

        fun mooreBellmanFord(graph: Graph, startId: Int): BellmanFordResult {
            return mooreBellmanFord(graph, startId, false)
        }

        fun mooreBellmanFord(graph: Graph, startId: Int, costWeighted: Boolean): BellmanFordResult {
            val visited = BooleanArray(graph.getVertexCount())
            val shortestPathElements = mutableListOf<ShortestPathElement>()
            for (i in 0 until graph.getVertexCount()) {
                val shortestPathElement = ShortestPathElement(i, Double.POSITIVE_INFINITY, null)
                shortestPathElements.add(shortestPathElement)
            }
            shortestPathElements[startId].distance = 0.0
            shortestPathElements[startId].predecessor = startId
            for (n in 0 until graph.getVertexCount() - 1) {
                compareAndUpdateDistances(graph, shortestPathElements, visited, costWeighted)
            }

            val shortestPathElementsLaststep = ArrayList(shortestPathElements)
            val cycleDetectedAt = compareAndUpdateDistances(graph, shortestPathElementsLaststep, visited, costWeighted)
            var cycle: Cycle? = null
            if (cycleDetectedAt != null) {
                cycle = extractCycle(graph, shortestPathElementsLaststep, cycleDetectedAt)
            }
            return BellmanFordResult(ArrayList(shortestPathElementsLaststep), cycle)
        }

        private fun compareAndUpdateDistances(
            graph: Graph,
            shortestPathElements: MutableList<ShortestPathElement>,
            visited: BooleanArray,
            costWeighted: Boolean
        ): Int? {
            var changedValueAt: Int? = null
            var v: Int
            var w: Int
            var weight: Double
            for (edge in graph.getEdges()) {
                v = edge.source.getId()
                w = edge.target.getId()
                visited[v] = true
                visited[w] = true
                if (costWeighted) {
                    weight = edge.cost
                } else {
                    weight = edge.capacity
                }
                val newDistV = shortestPathElements[v].distance + weight
                if (newDistV < shortestPathElements[w].distance) {
                    changedValueAt = w
                    shortestPathElements[w].distance = newDistV
                    shortestPathElements[w].predecessor = v
                }
            }
            return changedValueAt
        }

        private fun extractCycle(
            graph: Graph,
            shortestPathElementsLaststep: ArrayList<ShortestPathElement>,
            cycleDetectedAt: Int
        ): Cycle {
            println("Graph contains negative cycle")
            val map = HashMap<Int, ShortestPathElement>()
            for (shortestPathElement in shortestPathElementsLaststep) {
                map[shortestPathElement.vertexId] = shortestPathElement
            }
            val visited = BooleanArray(graph.getVertexCount())
            var shortestPathElement = map[cycleDetectedAt]!!
            while (shortestPathElement.predecessor != null && !visited[shortestPathElement.vertexId]) {
                visited[shortestPathElement.vertexId] = true
                shortestPathElement = map[shortestPathElement.predecessor!!]!!
            }
            val vertexInCycle = shortestPathElement.vertexId
            var lowestWeightedEdge =
                graph.getVertex(shortestPathElement.vertexId).getEdge(shortestPathElement.predecessor!!)
            var currentId = vertexInCycle
            var predecessor = shortestPathElement.predecessor!!
            val result = ArrayList<Edge>()
            while (vertexInCycle != predecessor) {
                val currentEdge = graph.getVertex(predecessor).getEdge(currentId)
                result.add(currentEdge)
                if (currentEdge.capacity < lowestWeightedEdge.capacity) {
                    lowestWeightedEdge = currentEdge
                }
                currentId = map[currentId]!!.predecessor!!
                predecessor = map[currentId]!!.predecessor!!
            }

            val currentEdge = graph.getVertex(predecessor).getEdge(currentId)
            result.add(currentEdge)

            return Cycle(result, lowestWeightedEdge)
        }
    }

    class ShortestPathElement(val vertexId: Int, var distance: Double, var predecessor: Int?) {
        override fun toString(): String {
            return "ShortestPathElement(vertexId=$vertexId, distance=$distance, predecessor=$predecessor)"
        }
    }

    class BellmanFordResult(
        var shortestPath: ArrayList<ShortestPathElement>,
        var cycle: Cycle?
    )

    class Cycle(
        val edges: List<Edge>?,
        val lowestWeightedEdge: Edge
    )
}