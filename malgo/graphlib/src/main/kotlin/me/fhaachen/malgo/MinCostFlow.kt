package me.fhaachen.malgo

class MinCostFlow {
    companion object {
        fun cycleCancelation(graph: DiGraph): MinCostFlowResult {
            // Superquellen, Supersenken
            // b-Fluss erzeugen
            val edmondsKarp = MaxFlow.edmondsKarp(graph, graph.getSuperSource().getId(), graph.getSuperSink().getId())
            if (edmondsKarp == null || !graph.isBalanced()) {
                return MinCostFlowResult(null)
            }
            // finde negative Zykel in Residualkosten
            var bellmanFordResult = ShortestPath.mooreBellmanFord(graph, graph.getSuperNode().getId(), true)
            var cyclesCancelled = 0
            var lowestWeight: Double
            while (bellmanFordResult.cycle != null) {
                lowestWeight = bellmanFordResult.cycle!!.lowestWeightedEdge.capacity
                for (edge in bellmanFordResult.cycle!!.edges!!) {
                    val target = graph.getVertex(edge.target.getId())
                    if (!target.hasOutgoingEdge(edge.source.getId())) {
                        graph.createResidualEdge(target, edge.source, -edge.cost)
                    }
                    val residualEdge = target.getOutgoingEdge(edge.source.getId())
                    residualEdge.updateFlow(lowestWeight)
                    val source = graph.getVertex(edge.source.getId())
                    var originalEdge: Edge? = null
                    if (source.hasOutgoingEdge(edge.target.getId())) {
                        originalEdge = source.getOutgoingEdge(edge.target.getId())
                    }
                    originalEdge!!.updateFlow(-lowestWeight)
                }
                bellmanFordResult = ShortestPath.mooreBellmanFord(graph, graph.getSuperNode().getId(), true)
                cyclesCancelled++
            }
            println("Cycles cancelled: $cyclesCancelled")
            var cost = 0.0
            for (edge in graph.getResidualEdges()) {
                cost += -edge.cost * edge.capacity
            }
            return MinCostFlowResult(cost)
        }

        fun successiveShortestPath(graph: DiGraph): MinCostFlowResult {
            // initialisiere "min. Fluss": neg. Kanten voll, Rest 0
            // Bestimme b'
            // Suche Pseudoquelle/Pseudosenke und bestimme ShortestCostPath
            // Abbruch, falls nicht möglich
            // ändere Fluss
            // gehe zu Schritt Bestimme b'
            return MinCostFlowResult(null)
        }
    }

    class MinCostFlowResult(var minCost: Double?) {
        override fun toString(): String {
            return "MinCostFlowResult(minCost=$minCost)"
        }
    }
}