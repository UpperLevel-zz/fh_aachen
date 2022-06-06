package me.fhaachen.malgo

import java.util.*
import kotlin.collections.HashSet

class DiGraph : Graph {

    private var vertices: HashMap<Int, Vertex> = HashMap()
    private var edges: HashSet<Edge> = HashSet()
    private var sources: HashMap<Int, Double> = HashMap()
    private var sinks: HashMap<Int, Double> = HashMap()

    override fun connectVertices(edge: Edge) {
        val source = vertices.getOrPut(edge.source.getId()) { edge.source }
        val target = vertices.getOrPut(edge.target.getId()) { edge.target }
        updateBalance(edge.source, edge.target)
        val edgeCopy = Edge(source, target, edge.capacity, edge.cost)
        source.addOutgoingEdge(edgeCopy)
        target.addIncomingEdge(edgeCopy)
        edges.add(edgeCopy)
    }

    private fun updateBalance(source: Vertex, target: Vertex) {
        if (source.getBalance() < 0) {
            sinks[source.getId()] = source.getBalance()
        } else if (source.getBalance() > 0) {
            sources[source.getId()] = source.getBalance()
        }
        if (target.getBalance() < 0) {
            sinks[target.getId()] = target.getBalance()
        } else if (target.getBalance() > 0) {
            sources[target.getId()] = target.getBalance()
        }
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

    override fun toAdjacentCapacities(): Array<DoubleArray> {
        val result = Array(vertices.size) { DoubleArray(vertices.size) { 0.0 } }
        for (edge in edges) {
            result[edge.source.getId()][edge.target.getId()] = edge.capacity
        }
        return result
    }

    override fun toString(): String {
        return "DiGraph(countVertex=${vertices.size}, countEdge=${edges.size}, edges=${edges}, vertices=$vertices)"
    }

}