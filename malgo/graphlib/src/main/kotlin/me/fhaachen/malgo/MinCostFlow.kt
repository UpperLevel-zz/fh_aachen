package me.fhaachen.malgo

class MinCostFlow {
    companion object {
        fun cycleCancelation(graph: DiGraph) {
            // Superquellen, Supersenken
            // b-Fluss erzeugen
            val bFlow = MaxFlow.edmondsKarp(graph, graph.getSuperSource().getId(), graph.getSuperSink().getId())
            println(bFlow)
            // Abbruch, falls nicht möglich
            // finde negative Zykel, falls keine: Fertig
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