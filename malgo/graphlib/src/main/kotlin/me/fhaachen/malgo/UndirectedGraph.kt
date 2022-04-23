package me.fhaachen.malgo

import main.kotlin.me.fhaachen.malgo.Edge
import java.util.*

class UndirectedGraph(size: Int) : Graph {

    private var vertices: HashMap<Int, Vertex> = HashMap()
    private var edges: ArrayList<Edge> = ArrayList()

    override fun connectVertices(edge: Edge) {
        val current = vertices.getOrDefault(edge.source.getId(), edge.source)
        val currentAdjacent = vertices.getOrDefault(edge.target.getId(), edge.target)
        when {
            current.addAdjacentVertex(currentAdjacent) -> {
                edges.add(edge)
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
        return edges.size
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