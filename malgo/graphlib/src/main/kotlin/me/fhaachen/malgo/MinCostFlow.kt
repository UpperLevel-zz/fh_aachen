package me.fhaachen.malgo

class MinCostFlow {
    companion object {
        fun cycleCancelation(graph: DiGraph) {
            // Superquellen, Supersenken
            // b-Fluss erzeugen
            val graphAsMatrix = graph.toAdjacentCapacities()
            val residualGraph = MaxFlow.edmondsKarp(graph, graph.getSuperSource().getId(), graph.getSuperSink().getId())
            // finde negative Zykel in Residualkosten, falls keine: Fertig
            val mooreBellmanFord = ShortestPath.mooreBellmanFord(residualGraph, graph.createSuperSource())
            var cost = 0.0
            for (edge in residualGraph.getEdges()) {
                if (graph.getVertex(edge.source.getId()).hasOutgoingEdge(edge.target.getId())) {
                    cost += edge.cost * edge.capacity
                }
            }
            println("MinCost: $cost")
            // bestimme gamma
            // ändere Fluss
            // gehe zu Schritt neg. Zykel finden
        }

        fun successiveShortestPath(graph: DiGraph) {
            // initialisiere "min. Fluss": neg. Kanten voll, Rest 0
            // Bestimme b'
            // Suche Pseudoquelle/Pseudosenke und bestimme ShortestCostPath
            // Abbruch, falls nicht möglich
            // ändere Fluss
            // gehe zu Schritt Bestimme b'
        }
    }
}