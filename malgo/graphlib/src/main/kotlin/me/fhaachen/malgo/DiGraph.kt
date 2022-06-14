package me.fhaachen.malgo

import java.util.*
import kotlin.math.abs

class DiGraph : Graph {

    private var vertices: HashMap<Int, Vertex> = HashMap()
    private var edges: HashSet<Edge> = HashSet()
    private var sources: HashMap<Int, Double> = HashMap()
    private var sinks: HashMap<Int, Double> = HashMap()
    private var residualEdges: HashSet<Edge> = HashSet()
    private lateinit var superSource: Vertex
    private lateinit var superSink: Vertex

    override fun connectVertices(edge: Edge, residual: Boolean) {
        val source = vertices.getOrPut(edge.source.getId()) { edge.source }
        val target = vertices.getOrPut(edge.target.getId()) { edge.target }
        updateBalance(edge.source, edge.target)
        val edgeCopy = Edge(source, target, edge.capacity, edge.cost)
        source.addOutgoingEdge(edgeCopy)
        target.addIncomingEdge(edgeCopy)
        edges.add(edgeCopy)
        if (residual) {
            residualEdges.add(edgeCopy)
        }
    }

    private fun updateBalance(source: Vertex, target: Vertex) {
        if ((this::superSource.isInitialized && source.getId() == superSource.getId())
            || (this::superSink.isInitialized && target.getId() == superSink.getId())
        ) {
            return
        }
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

    fun getResidualEdges(): LinkedList<Edge> {
        return LinkedList(residualEdges)
    }

    fun getSuperSource(): Vertex {
        return superSource
    }

    fun getSuperSink(): Vertex {
        return superSink
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

    fun postInit() {
        createSuperSource()
        createSuperSink()
    }

    override fun toString(): String {
        return "DiGraph(countVertex=${vertices.size}, countEdge=${edges.size}, edges=${edges}, vertices=$vertices)"
    }

    fun createSuperSource(): Int {
        if (this::superSource.isInitialized) {
            return superSource.getId()
        }
        superSource = Vertex(vertices.size)
        for (source in sources) {
            val edge = Edge(superSource, Vertex(source.key), abs(source.value), 0.0)
            this.connectVertices(edge)
            superSource.updateBalance(source.value)
        }
        return superSource.getId()
    }

    fun createSuperSink(): Int {
        if (this::superSink.isInitialized) {
            return superSink.getId()
        }
        superSink = Vertex(vertices.size)
        for (sink in sinks) {
            val edge = Edge(Vertex(sink.key), superSink, abs(sink.value), 0.0)
            this.connectVertices(edge)
            superSink.updateBalance(sink.value)
        }
        return superSink.getId()
    }

}