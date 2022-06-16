package me.fhaachen.malgo

import java.util.*

class HamiltonianCycle : Graph {

    private var vertices: HashMap<Int, Vertex> = HashMap()
    private var edges: ArrayList<Edge> = ArrayList()

    override fun connectVertices(edge: Edge, residual: Boolean) {
        vertices[edge.source.getId()] = edge.source
        vertices[edge.target.getId()] = edge.target
        edges.add(edge)
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

    override fun getAdditionalVertexCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getEdges(): LinkedList<Edge> {
        return LinkedList(edges)
    }

    override fun isEmpty(): Boolean {
        return vertices.isEmpty()
    }

    override fun toAdjacentCapacities(): Array<DoubleArray> {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "HamiltonianCyle(countVertex=${vertices.size}, countEdge=${edges.size}, edges=${edges}"
    }

}