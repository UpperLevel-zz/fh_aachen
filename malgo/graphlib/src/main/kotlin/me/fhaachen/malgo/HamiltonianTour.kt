package me.fhaachen.malgo

import java.util.*

class HamiltonianTour {
    var optimalCycle = LinkedList<Vertex>()
    var lowestAmount = Double.POSITIVE_INFINITY

    fun findMostFavorable(graph: Graph, useBound: Boolean) {
        findMostFavorable(
            useBound,
            graph,
            graph.getVertices().first,
            BooleanArray(graph.getVertexCount()),
            0.0,
            LinkedList()
        )
    }

    private fun findMostFavorable(
        useBound: Boolean,
        graph: Graph,
        currentVertex: Vertex,
        visitedIndices: BooleanArray,
        amount: Double,
        hamiltonianCycle: LinkedList<Vertex>
    ) {
        hamiltonianCycle.add(currentVertex)
        if (hamiltonianCycle.size == graph.getVertexCount()) {
            hamiltonianCycle.add(graph.getVertices().first)
            val edge = currentVertex.getEdge(graph.getVertices().first.getId())
            val newAmout = amount + edge.capacity!!
            if ((lowestAmount > newAmout)) {
                optimalCycle = hamiltonianCycle
                lowestAmount = newAmout
            }
        }

        visitedIndices[currentVertex.getId()] = true
        for (adjecentVertex in currentVertex.getAdjacentVertices()) {
            if (!visitedIndices[adjecentVertex.getId()]) {
                val edge = currentVertex.getEdge(adjecentVertex.getId())
                // Branch and Bound Schranke
                if (useBound && amount + edge.capacity!! > lowestAmount) {
                    continue
                }
                findMostFavorable(
                    useBound,
                    graph,
                    adjecentVertex,
                    visitedIndices.clone(),
                    amount + edge.capacity!!,
                    LinkedList(hamiltonianCycle)
                )
            }
        }
    }

}