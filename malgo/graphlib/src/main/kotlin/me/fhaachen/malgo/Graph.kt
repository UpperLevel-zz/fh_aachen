package me.fhaachen.malgo

import java.util.*

interface Graph {
    fun connectVertices(edge: Edge)
    fun getIds(): LinkedList<Int>
    fun getVertices(): LinkedList<Vertex>
    fun getVertex(id: Int): Vertex
    fun getEdges(): LinkedList<Edge>
    fun getEdgeCount(): Int
    fun getVertexCount(): Int
    fun isEmpty(): Boolean
}