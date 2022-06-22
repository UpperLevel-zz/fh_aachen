package me.fhaachen.malgo

import java.util.*
import kotlin.math.abs
import kotlin.math.min

class MinCostFlow {
    companion object {
        fun cycleCancelation(graph: DiGraph): MinCostFlowResult {
            if (!graph.checkOverallBalance()) {
                return MinCostFlowResult(null)
            }
            graph.createSuperSource()
            graph.createSuperSink()
            val edmondsKarp =
                MaxFlow.edmondsKarp(graph, graph.getSuperSource()!!.getId(), graph.getSuperSink()!!.getId())
            if (edmondsKarp == null || !graph.isSuperSourceBalanced()) {
                println("No balanced-Fow possible")
                return MinCostFlowResult(null)
            }
            graph.createSuperNode()
            // find negative cost cycle in residual graph
            var bellmanFordResult = ShortestPath.mooreBellmanFord(graph, graph.getSuperNode()!!.getId(), true)
            var cyclesCancelled = 0
            var lowestWeight: Double
            while (bellmanFordResult.cycle != null) {
                lowestWeight = bellmanFordResult.cycle!!.lowestWeightedEdge.capacity
                for (edge in bellmanFordResult.cycle!!.edges!!) {
                    val source = graph.getVertex(edge.source.getId())
                    val forwardEdge = source.getOutgoingEdge(edge.target.getId())
                    var resultingWeight = lowestWeight
                    if (forwardEdge.residual) {
                        resultingWeight = -lowestWeight
                    }
                    forwardEdge.updateFlow(resultingWeight)
                    val target = graph.getVertex(edge.target.getId())
                    if (!target.hasOutgoingEdge(edge.source.getId())) {
                        graph.createResidualEdge(target, edge.source, -edge.cost)
                    }
                    val reverseEdge = target.getOutgoingEdge(edge.source.getId())
                    reverseEdge.updateFlow(resultingWeight)
                }
                bellmanFordResult = ShortestPath.mooreBellmanFord(graph, graph.getSuperNode()!!.getId(), true)
                cyclesCancelled++
            }
            println("Cycles cancelled: $cyclesCancelled")
            return MinCostFlowResult(graph.getFlowCost())
        }

        fun successiveShortestPath(graph: DiGraph): MinCostFlowResult {
            if (!graph.checkOverallBalance()) {
                return MinCostFlowResult(null)
            }
            // initialisiere "min. Fluss": neg. Kanten voll, Rest 0
            for (edge in graph.getEdges()) {
                if (edge.cost < 0) {
                    edge.updateFlow(edge.capacity)
                    if (!graph.getVertex(edge.target.getId()).hasOutgoingEdge(edge.source.getId())) {
                        graph.createResidualEdge(edge.target, edge.source, -edge.cost)
                    }
                    val reversedEdge = graph.getVertex(edge.target.getId()).getOutgoingEdge(edge.source.getId())
                    reversedEdge.updateFlow(edge.flow)
                } else {
                    edge.updateFlow(0.0)
                }
            }
            var source: Vertex?
            var shortestPathToSink: ShortestPathWithWeight
            while (!graph.isBalanced()) {
                source = graph.getNextSource()
                if (source == null) {
                    println("Source must not be null @ this point")
                    return MinCostFlowResult(null)
                }
                shortestPathToSink = getShortestPathToSink(graph, source)
                if (shortestPathToSink.queue.isEmpty()) {
                    println("No more balancing possible")
                    return MinCostFlowResult(null)
                }
                while (shortestPathToSink.queue.isNotEmpty()) {
                    val edge = shortestPathToSink.queue.poll()
                    var resultingWeight = shortestPathToSink.lowestWeight!!
                    if (edge.residual) {
                        resultingWeight = -resultingWeight
                    }
                    if (!graph.getVertex(edge.target.getId()).hasOutgoingEdge(edge.source.getId())) {
                        graph.createResidualEdge(edge.target, edge.source, -edge.cost)
                    }
                    val reversedEdge = graph.getVertex(edge.target.getId()).getOutgoingEdge(edge.source.getId())
                    edge.updateFlow(resultingWeight)
                    reversedEdge.updateFlow(resultingWeight)
                }
            }
            return MinCostFlowResult(graph.getFlowCost())
        }

        private fun getShortestPathToSink(graph: DiGraph, source: Vertex): ShortestPathWithWeight {
            val mooreBellmanFord = ShortestPath.mooreBellmanFord(graph, source.getId(), true)
            if (mooreBellmanFord.cycle != null) {
                throw java.lang.IllegalStateException("There should never be a negative cycle in here!")
            }
            val queue = LinkedList<Edge>()
            var lowestFlowPossible: Double? = null
            for (sPath in mooreBellmanFord.shortestPathTree) {
                if (sPath.distance < Double.POSITIVE_INFINITY
                    && sPath.vertexId != source.getId()
                    && graph.getVertex(sPath.vertexId).pseudoBalance < 0
                ) {
                    var currentElement = sPath
                    lowestFlowPossible =
                        min(source.pseudoBalance, abs(graph.getVertex(currentElement.vertexId).pseudoBalance))
                    while (currentElement.predecessor != null && currentElement.vertexId != source.getId()) {
                        val edge = graph.getVertex(currentElement.predecessor!!)
                            .getOutgoingEdge(currentElement.vertexId)
                        lowestFlowPossible = min(edge.capacity, lowestFlowPossible!!)
                        queue.add(edge)
                        currentElement = mooreBellmanFord.shortestPathTree[currentElement.predecessor!!]
                    }
                    break
                }
            }
            return ShortestPathWithWeight(queue, lowestFlowPossible)
        }
    }

    class ShortestPathWithWeight(val queue: Queue<Edge>, val lowestWeight: Double?)
    class MinCostFlowResult(var minCost: Double?) {
        override fun toString(): String {
            return "MinCostFlowResult(minCost=$minCost)"
        }
    }
}