package me.fhaachen.malgo

import java.util.*
import kotlin.collections.HashMap

class UndirectedGraph(size: Int) : Graph {

    private var vertices: HashMap<Int, Vertex> = HashMap()
    var edges = 0

    override fun connectVertices(vertex: Vertex, adjacent: Vertex) {
        val current = vertices.getOrDefault(vertex.getId(), vertex)
        val currentAdjacent = vertices.getOrDefault(adjacent.getId(), adjacent)
        when {
            current.addAdjacentVertex(currentAdjacent) -> {
                edges++
            }
        }
    }

    override fun getIds(): LinkedList<Int> {
        return LinkedList(vertices.values.sortedBy { x -> x.getId() }.stream().map { x -> x.getId() }.toList())
    }

    override fun getVertex(id: Int): Vertex {
        return vertices[id]!!
    }

    override fun getEdgeCount(): Int {
        return edges
    }

    override fun isEmpty(): Boolean {
        return vertices.isEmpty()
    }

    override fun toString(): String {
        return "MyGraph(countVertex=${vertices.size}, countEdge=${edges}, vertices=$vertices)"
    }

    init {
        for (i in 0 until size) {
            this.vertices[i] = Vertex(i)
        }
    }

}