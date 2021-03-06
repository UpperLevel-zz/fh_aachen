package me.fhaachen.malgo

import java.util.*

interface Graph {
    fun connectVertices(edge: Edge, residual: Boolean)
    fun connectVertices(edge: Edge) {
        this.connectVertices(edge, false)
    }

    fun getIds(): LinkedList<Int>
    fun getVertices(): LinkedList<Int>
    fun getVertex(id: Int): Vertex
    fun getEdges(): LinkedList<Edge>
    fun getEdgeCount(): Int
    fun getVertexCount(): Int
    fun getAdditionalVertexCount(): Int
    fun capacity(): Double {
        var amount = 0.0
        for (edge in getEdges()) {
            amount += edge.capacity
        }
        return amount
    }

    fun isEmpty(): Boolean

    fun toAdjacentCapacities(): Array<DoubleArray>
}