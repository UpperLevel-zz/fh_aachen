package me.fhaachen.malgo

import java.util.*

class DiGraph(size: Int) : Graph {

    private var vertices: HashMap<Int, Vertex> = HashMap()
    private var edges: ArrayList<Edge> = ArrayList()

    override fun connectVertices(edge: Edge) {
        val source = vertices.getOrDefault(edge.source.getId(), edge.source)
        val target = vertices.getOrDefault(edge.target.getId(), edge.target)
        val edgeCopy = Edge(source, target, edge.capacity)
        source.addOutgoingEdge(edgeCopy)
        target.addIncomingEdge(edgeCopy)
        edges.add(edgeCopy)
    }

    override fun getIds(): LinkedList<Int> {
        return LinkedList(vertices.values.sortedBy { x -> x.getId() }.stream().map { x -> x.getId() }.toList())
    }

    override fun getVertices(): LinkedList<Int> {
        return LinkedList(vertices.keys)
    }

    override fun getVertex(id: Int): Vertex {
        return vertices[id]!!
    }

    override fun getEdgeCount(): Int {
        return edges.size
    }

    override fun getVertexCount(): Int {
        return vertices.size
    }

    override fun getEdges(): LinkedList<Edge> {
        return LinkedList(edges)
    }

    override fun isEmpty(): Boolean {
        return vertices.isEmpty()
    }

    override fun toString(): String {
        return "DiGraph(countVertex=${vertices.size}, countEdge=${edges.size}, edges=${edges}, vertices=$vertices)"
    }

    init {
        for (i in 0 until size) {
            this.vertices[i] = Vertex(i)
        }
    }

}