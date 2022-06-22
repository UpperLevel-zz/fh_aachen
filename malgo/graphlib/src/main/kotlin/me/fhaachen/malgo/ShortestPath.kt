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
                for (outgoingEdge in vertex.outgoingEdges.values) {
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
            val shortestPathElements = arrayListOf<ShortestPathElement>()
            for (i in 0 until graph.getVertexCount()) {
                val shortestPathElement = ShortestPathElement(i, Double.POSITIVE_INFINITY, null)
                shortestPathElements.add(shortestPathElement)
            }
            shortestPathElements[startId].distance = 0.0
            shortestPathElements[startId].predecessor = startId
            val vertexCountMinusOne = graph.getVertexCount() - graph.getAdditionalVertexCount() - 1
            for (n in 0 until vertexCountMinusOne) {
                compareAndUpdateDistances(graph, shortestPathElements, visited, costWeighted)
                    ?: return BellmanFordResult(ArrayList(shortestPathElements), null)
            }

            val cycleDetectedAt = compareAndUpdateDistances(graph, shortestPathElements, visited, costWeighted)
            var cycle: Cycle? = null
            if (cycleDetectedAt != null) {
                cycle = extractCycle(graph, shortestPathElements, cycleDetectedAt)
            }
            return BellmanFordResult(ArrayList(shortestPathElements), cycle)
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
                    if (costWeighted && edge.capacity <= 0.0
                        || costWeighted && edge.residual && edge.flow != 0.0
                    ) {
                        continue
                    }
                    if (changedValueAt == null) {
                        changedValueAt = w
                    }
                    shortestPathElements[w].distance = newDistV
                    shortestPathElements[w].predecessor = v
                }
            }
            return changedValueAt
        }

        private fun extractCycle(
            graph: Graph,
            shortestPathElementsLaststep: ArrayList<ShortestPathElement>,
            valueChangedAt: Int
        ): Cycle {
            val map = HashMap<Int, ShortestPathElement>()
            for (shortestPathElement in shortestPathElementsLaststep) {
                map[shortestPathElement.vertexId] = shortestPathElement
            }
            val visited = BooleanArray(graph.getVertexCount())
            var shortestPathElement = map[valueChangedAt]!!
            for (i in 0 until shortestPathElementsLaststep.size - 1) {
                if (visited[shortestPathElement.vertexId]) {
                    break
                }
                visited[shortestPathElement.vertexId] = true
                shortestPathElement = map[shortestPathElement.predecessor!!]!!
            }
            val vertexInCycle = shortestPathElement.vertexId
            println("Graph contains negative cycle @Vertex = $vertexInCycle")
            var lowestWeightedEdge: Edge? = null
            var currentId = vertexInCycle
            var predecessor = shortestPathElement.predecessor!!
            val cycle = ArrayList<Edge>()
            //todo: eventuell bis currentId laufen
            while (vertexInCycle != predecessor) {
                val currentEdge = graph.getVertex(predecessor).getOutgoingEdge(currentId)
                cycle.add(currentEdge)
                if (lowestWeightedEdge == null || currentEdge.capacity < lowestWeightedEdge.capacity) {
                    lowestWeightedEdge = currentEdge
                }
                currentId = map[currentId]!!.predecessor!!
                predecessor = map[currentId]!!.predecessor!!
            }

            val currentEdge = graph.getVertex(predecessor).getOutgoingEdge(currentId)
            if (lowestWeightedEdge == null || currentEdge.capacity < lowestWeightedEdge.capacity) {
                lowestWeightedEdge = currentEdge
            }
            cycle.add(currentEdge)
            return Cycle(cycle, lowestWeightedEdge)
        }
    }

    class ShortestPathElement(val vertexId: Int, var distance: Double, var predecessor: Int?) {
        override fun toString(): String {
            return "ShortestPathElement(vertexId=$vertexId, distance=$distance, predecessor=$predecessor)"
        }
    }

    class BellmanFordResult(
        var shortestPathTree: ArrayList<ShortestPathElement>,
        var cycle: Cycle?
    ) {
        override fun toString(): String {
            return "BellmanFordResult(shortestPath=$shortestPathTree, cycle=$cycle)"
        }
    }

    class Cycle(
        val edges: List<Edge>?,
        val lowestWeightedEdge: Edge
    ) {
        override fun toString(): String {
            return "Cycle(edges=$edges, lowestWeightedEdge=$lowestWeightedEdge)"
        }
    }
}