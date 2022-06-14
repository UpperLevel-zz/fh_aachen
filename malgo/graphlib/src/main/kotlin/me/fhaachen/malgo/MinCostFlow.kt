package me.fhaachen.malgo

class MinCostFlow {
    companion object {
        fun cycleCancelation(graph: DiGraph): MinCostFlowResult {
            // Superquellen, Supersenken
            // b-Fluss erzeugen
            val maxFlowResult = MaxFlow.edmondsKarp(graph, graph.getSuperSource().getId(), graph.getSuperSink().getId())
            // finde negative Zykel in Residualkosten
            var bellmanFordResult =
                ShortestPath.mooreBellmanFord(maxFlowResult.lastResidualGraph, graph.createSuperSource(), true)
            while (bellmanFordResult.cycle != null && bellmanFordResult.cycle!!.lowestWeightedEdge.capacity > 0) {
                for (edge in bellmanFordResult.cycle!!.edges!!) {
                    val target = maxFlowResult.lastResidualGraph.getVertex(edge.target.getId())
                    if (target.hasOutgoingEdge(edge.source.getId())) {
                        val residualEdge = target.getOutgoingEdge(edge.source.getId())
                        residualEdge.capacity += bellmanFordResult.cycle!!.lowestWeightedEdge.capacity
                    } else {
                        val residualEdge =
                            Edge(target, edge.source, bellmanFordResult.cycle!!.lowestWeightedEdge.capacity, -edge.cost)
                        maxFlowResult.lastResidualGraph.connectVertices(residualEdge, true)
                    }
                    val source = maxFlowResult.lastResidualGraph.getVertex(edge.source.getId())
                    if (source.hasOutgoingEdge(edge.target.getId())) {
                        val originalEdge = source.getOutgoingEdge(edge.target.getId())
                        originalEdge.capacity -= bellmanFordResult.cycle!!.lowestWeightedEdge.capacity
                    }
                }
                bellmanFordResult =
                    ShortestPath.mooreBellmanFord(maxFlowResult.lastResidualGraph, graph.createSuperSource(), true)
            }
            var cost = 0.0
            for (edge in maxFlowResult.lastResidualGraph.getResidualEdges()) {
                cost += -edge.cost * edge.capacity
            }
            return MinCostFlowResult(cost, maxFlowResult.lastResidualGraph)
        }

        fun successiveShortestPath(graph: DiGraph): MinCostFlowResult {
            // initialisiere "min. Fluss": neg. Kanten voll, Rest 0
            // Bestimme b'
            // Suche Pseudoquelle/Pseudosenke und bestimme ShortestCostPath
            // Abbruch, falls nicht möglich
            // ändere Fluss
            // gehe zu Schritt Bestimme b'
            throw java.lang.UnsupportedOperationException("Not yet implemented")
        }
    }

    class MinCostFlowResult(var minCost: Double, var lastResidualGraph: Graph) {

        override fun toString(): String {
            return "MinCostFlowResult(minCost=$minCost, lastResidualGraph=$lastResidualGraph)"
        }
    }
}