package me.fhaachen.malgo

import java.util.*

interface Graph {
    fun connectVertices(vertex: Vertex, adjacent: Vertex)
    fun getIds(): LinkedList<Int>
    fun getVertex(id: Int) : Vertex
    fun getEdgeCount(): Int
    fun isEmpty(): Boolean
}