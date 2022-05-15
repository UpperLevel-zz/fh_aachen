package me.fhaachen.malgo

import java.util.*

class HamiltonianTour {
    var optimalCycle = LinkedList<Vertex>()
    var lowestAmount = Double.POSITIVE_INFINITY

    fun findMostFavorable(graph: Graph, useBound: Boolean) {
        val visitedIndices = BooleanArray(graph.getVertexCount())
        val startVertex = graph.getVertices().first
        visitedIndices[startVertex.getId()] = true
        val hamiltonianCycle = LinkedList<Vertex>()
        hamiltonianCycle.add(startVertex)
        findMostFavorable(
            useBound,
            graph,
            startVertex,
            visitedIndices,
            0.0,
            hamiltonianCycle
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
        if (hamiltonianCycle.size == graph.getVertexCount()) {
            hamiltonianCycle.add(graph.getVertices().first)
            val edge = currentVertex.getEdge(graph.getVertices().first.getId())
            val newAmout = amount + edge.capacity!!
            if ((lowestAmount > newAmout)) {
                optimalCycle = LinkedList(hamiltonianCycle)
                lowestAmount = newAmout
            }
            hamiltonianCycle.removeLast()
        }

        for (adjecentVertex in currentVertex.getAdjacentVertices()) {
            if (!visitedIndices[adjecentVertex.getId()]) {
                val edge = currentVertex.getEdge(adjecentVertex.getId())
                // Branch and Bound Schranke
                if (useBound && amount + edge.capacity!! > lowestAmount) {
                    continue
                }

                visitedIndices[adjecentVertex.getId()] = true
                hamiltonianCycle.add(adjecentVertex)
                findMostFavorable(
                    useBound,
                    graph,
                    adjecentVertex,
                    visitedIndices,
                    amount + edge.capacity!!,
                    hamiltonianCycle
                )
                hamiltonianCycle.removeLast()
                visitedIndices[adjecentVertex.getId()] = false
            }
        }
    }

}