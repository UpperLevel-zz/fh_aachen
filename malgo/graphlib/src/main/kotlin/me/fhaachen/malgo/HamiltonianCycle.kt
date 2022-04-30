package me.fhaachen.malgo

import main.kotlin.me.fhaachen.malgo.Edge
import java.util.*

class HamiltonianCycle : Graph, Cycle {

    private var vertices: HashMap<Int, Vertex> = HashMap()
    private var edges: ArrayList<Edge> = ArrayList()

    override fun connectVertices(edge: Edge) {
        if (vertices.contains(edge.target.getId())) {
            throw ArithmeticException("Hamiltonian Cycles must not contain duplicate vertices")
        }
        vertices.put(edge.source.getId(), edge.source)
        vertices.put(edge.target.getId(), edge.target)
        edges.add(edge)
    }

    override fun getIds(): LinkedList<Int> {
        return LinkedList(vertices.values.sortedBy { x -> x.getId() }.stream().map { x -> x.getId() }.toList())
    }

    override fun getVertices(): LinkedList<Vertex> {
        return LinkedList(vertices.values)
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
        return "HamiltonianCyle(countVertex=${vertices.size}, countEdge=${edges.size}, edges=${edges}, vertices=$vertices)"
    }

}