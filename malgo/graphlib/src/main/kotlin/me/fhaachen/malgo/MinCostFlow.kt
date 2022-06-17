package me.fhaachen.malgo

import java.util.*
import kotlin.math.min

class MinCostFlow {
    companion object {
        fun cycleCancelation(graph: DiGraph): MinCostFlowResult {
            if (!graph.checkOverallBalance()) {
                return MinCostFlowResult(null)
            }
            val edmondsKarp = MaxFlow.edmondsKarp(graph, graph.getSuperSource().getId(), graph.getSuperSink().getId())
            if (edmondsKarp == null || !graph.isSuperSourceBalanced()) {
                println("No balanced-Fow possible")
                return MinCostFlowResult(null)
            }
            // find negative cost cycle in residual graph
            var bellmanFordResult = ShortestPath.mooreBellmanFord(graph, graph.getSuperNode().getId(), true)
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
                bellmanFordResult = ShortestPath.mooreBellmanFord(graph, graph.getSuperNode().getId(), true)
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
            var source: Vertex? = graph.getSuperSource()
            val sink: Vertex = graph.getSuperSink()
            MaxFlow.edmondsKarp(graph, source!!.getId(), sink.getId()) ?: return MinCostFlowResult(null)
            val edmondsKarp = MaxFlow.edmondsKarp(graph, graph.getSuperSource().getId(), graph.getSuperSink().getId())
            if (edmondsKarp == null || !graph.isSuperSourceBalanced()) {
                println("No balanced-flow possible")
                return MinCostFlowResult(null)
            }

            while (!graph.isBalanced()) {
                source = graph.getNextSource()
                if (source == null) {
                    println("No more balancing possible")
                    return MinCostFlowResult(null)
                }

                val mooreBellmanFord = ShortestPath.mooreBellmanFord(graph, source.getId(), true)
                if (mooreBellmanFord.cycle != null) {
                    throw java.lang.IllegalStateException("There should never be a negative cycle in here!")
                }
                val queue = LinkedList<Edge>()
                var lowestFlowPossible = Double.POSITIVE_INFINITY
                for (sPath in mooreBellmanFord.shortestPath) {
                    if (sPath.distance < Double.POSITIVE_INFINITY
                        && sPath.distance > 0
                        && graph.getVertex(sPath.vertexId).pseudoBalance < 0
                    ) {
                        var currentElement = sPath
                        while (currentElement.predecessor != null && currentElement.vertexId != source.getId()) {

                            val edge = graph.getVertex(currentElement.predecessor!!)
                                .getOutgoingEdge(currentElement.vertexId)
                            if (edge.capacity < lowestFlowPossible) {
                                lowestFlowPossible = min(edge.capacity, source.pseudoBalance)
                            }
                            queue.add(edge)
                            currentElement = mooreBellmanFord.shortestPath[currentElement.predecessor!!]
                        }
                        if (queue.isEmpty()) {
                            println("No more balancing possible")
                            return MinCostFlowResult(null)
                        }
                        while (queue.isNotEmpty()) {
                            val edge = queue.poll()
                            var resultingWeight = lowestFlowPossible
                            if (edge.residual) {
                                resultingWeight = -lowestFlowPossible
                            }
                            if (!graph.getVertex(edge.target.getId()).hasOutgoingEdge(edge.source.getId())) {
                                graph.createResidualEdge(edge.target, edge.source, -edge.cost)
                            }
                            val reversedEdge = graph.getVertex(edge.target.getId()).getOutgoingEdge(edge.source.getId())
                            // why does the flow have to be negative
                            edge.updateFlow(resultingWeight)
                            reversedEdge.updateFlow(resultingWeight)
                        }
                        break
                    }
                }
            }
            return MinCostFlowResult(graph.getFlowCost())
        }
    }

    class MinCostFlowResult(var minCost: Double?) {
        override fun toString(): String {
            return "MinCostFlowResult(minCost=$minCost)"
        }
    }
}