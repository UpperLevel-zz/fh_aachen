package me.fhaachen.malgo

import java.util.*

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
                    forwardEdge.updateFlow(-lowestWeight)
                    val target = graph.getVertex(edge.target.getId())
                    if (!target.hasOutgoingEdge(edge.source.getId())) {
                        graph.createResidualEdge(target, edge.source, -edge.cost)
                    }
                    val reverseEdge = target.getOutgoingEdge(edge.source.getId())
                    reverseEdge.updateFlow(lowestWeight)
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
                } else {
                    edge.updateFlow(0.0)
                }
            }
            var source: Vertex? = graph.getSuperSource()
            val sink: Vertex = graph.getSuperSink()
            println(graph.getFlowCost())
            MaxFlow.edmondsKarp(graph, source!!.getId(), sink.getId()) ?: return MinCostFlowResult(null)
            val edmondsKarp = MaxFlow.edmondsKarp(graph, graph.getSuperSource().getId(), graph.getSuperSink().getId())
            if (edmondsKarp == null || !graph.isSuperSourceBalanced()) {
                println("No balanced-flow possible")
                return MinCostFlowResult(null)
            }

            while (!graph.isBalanced()) {
                // Bestimme b'
                // Suche Pseudoquelle/Pseudosenke und bestimme ShortestCostPath
                // Abbruch, falls nicht möglich
                // ändere Fluss
                source = graph.getNextSink()
                if (source == null) {
                    throw java.lang.IllegalStateException(
                        "If graph is not balanced there" +
                                " should be an unbalanced pair of sources and sinks"
                    )
                }

                val mooreBellmanFord = ShortestPath.mooreBellmanFord(graph, source.getId(), false)
                if (mooreBellmanFord.cycle != null) {
                    throw java.lang.IllegalStateException("There should never be a negative cycle in here!")
                }
                val queue = LinkedList<Edge>()
                var lowestCapacity = Double.POSITIVE_INFINITY
                for (sPath in mooreBellmanFord.shortestPath) {
                    if (sPath.distance < Double.POSITIVE_INFINITY
                        && graph.getVertex(sPath.vertexId).pseudoBalance > 0
                    ) {
                        var currentElement = sPath
                        while (currentElement.predecessor != null && currentElement.vertexId != source.getId()) {

                            val edge = graph.getVertex(currentElement.predecessor!!)
                                .getOutgoingEdge(currentElement.vertexId)
                            if (edge.capacity < lowestCapacity) {
                                lowestCapacity = edge.capacity
                            }
                            queue.add(edge)
                            currentElement = mooreBellmanFord.shortestPath[currentElement.predecessor!!]
                        }
                        while (queue.isNotEmpty()) {
                            val edge = queue.poll()
                            if (lowestCapacity < Double.POSITIVE_INFINITY) {
                                edge.updateFlow(-lowestCapacity)
                            } else {
                                edge.updateFlow(-edge.capacity)
                            }
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