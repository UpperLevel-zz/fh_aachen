package me.fhaachen.malgo

import main.kotlin.me.fhaachen.malgo.Edge
import java.util.*

interface Graph {
    fun connectVertices(edge: Edge)
    fun getIds(): LinkedList<Int>
    fun getVertices(): LinkedList<Vertex>
    fun getVertex(id: Int): Vertex
    fun getEdgeCount(): Int
    fun size(): Int
    fun getEdges(): LinkedList<Edge>
    fun isEmpty(): Boolean
}