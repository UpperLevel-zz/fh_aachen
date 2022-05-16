package me.fhaachen.malgo

import java.util.*

class HamiltonianTour {

    var optimalCycle = LinkedList<Int>()
    var lowestAmount = Double.POSITIVE_INFINITY

    fun findMostFavorable(graph: Graph, useBound: Boolean) {
        val visitedIndices = BooleanArray(graph.getVertexCount())
        val startId = graph.getVertices().first
        visitedIndices[startId] = true
        val hamiltonianCycle = LinkedList<Int>()
        hamiltonianCycle.add(startId)
        findMostFavorable(
            useBound,
            graph,
            startId,
            visitedIndices,
            0.0,
            hamiltonianCycle
        )
    }

    private fun findMostFavorable(
        useBound: Boolean,
        graph: Graph,
        currentId: Int,
        visitedIndices: BooleanArray,
        amount: Double,
        hamiltonianCycle: LinkedList<Int>
    ) {
        if (hamiltonianCycle.size == graph.getVertexCount()) {
            hamiltonianCycle.add(graph.getVertices().first)
            val edge = graph.getVertex(currentId).getEdge(graph.getVertices().first)
            val newAmout = amount + edge.capacity!!
            if ((lowestAmount > newAmout)) {
                optimalCycle = LinkedList(hamiltonianCycle)
                lowestAmount = newAmout
            }
            hamiltonianCycle.removeLast()
        }

        for (adjecentVertex in graph.getVertex(currentId).getAdjacentVertices()) {
            if (!visitedIndices[adjecentVertex]) {
                val edge = graph.getVertex(currentId).getEdge(adjecentVertex)
                // Branch and Bound Schranke
                if (useBound && amount + edge.capacity!! > lowestAmount) {
                    continue
                }

                visitedIndices[adjecentVertex] = true
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
                visitedIndices[adjecentVertex] = false
            }
        }
    }

}