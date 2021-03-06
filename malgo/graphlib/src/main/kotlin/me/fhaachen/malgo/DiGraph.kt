package me.fhaachen.malgo

import java.util.*
import kotlin.math.abs

class DiGraph : Graph {

    private var vertices: HashMap<Int, Vertex> = HashMap()
    private var edges: HashSet<Edge> = HashSet()
    private var sources: HashMap<Int, Vertex> = HashMap()
    private var sinks: HashMap<Int, Vertex> = HashMap()
    private var residualEdges: HashSet<Edge> = HashSet()
    private var superSource: Vertex? = null
    private var superSink: Vertex? = null
    private var superNode: Vertex? = null

    override fun connectVertices(edge: Edge, residual: Boolean) {
        val source = vertices.getOrPut(edge.source.getId()) { edge.source }
        val target = vertices.getOrPut(edge.target.getId()) { edge.target }
        edge.source = source
        edge.target = target
        updateBalance(edge.source, edge.target)
        source.addOutgoingEdge(edge)
        target.addIncomingEdge(edge)
        edges.add(edge)
    }

    fun createResidualEdge(
        source: Vertex,
        target: Vertex,
        cost: Double
    ): Edge {
        if (!source.hasOutgoingEdge(target.getId())) {
            val edge = Edge(source, target, 0.0, cost, 0.0, true)
            source.addOutgoingEdge(edge)
            target.addIncomingEdge(edge)
            residualEdges.add(edge)
            connectVertices(edge, true)
        }
        return source.getOutgoingEdge(target.getId())
    }

    private fun updateBalance(source: Vertex, target: Vertex) {
        if ((superSource != null && source.getId() == superSource!!.getId())
            || (superSink != null && target.getId() == superSink!!.getId())
        ) {
            return
        }
        if (source.getBalance() < 0) {
            sinks[source.getId()] = vertices[source.getId()]!!
        } else if (source.getBalance() > 0) {
            sources[source.getId()] = vertices[source.getId()]!!
        }
        if (target.getBalance() < 0) {
            sinks[target.getId()] = vertices[target.getId()]!!
        } else if (target.getBalance() > 0) {
            sources[target.getId()] = vertices[target.getId()]!!
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

    override fun getAdditionalVertexCount(): Int {
        var result = 0
        if (superNode != null) {
            result++
        }
        if (superSource != null) {
            result++
        }
        if (superSink != null) {
            result++
        }

        return result
    }

    fun checkOverallBalance(): Boolean {
        var balance = 0.0
        for (vertex in vertices.values) {
            balance += vertex.getBalance()
        }
        if (balance != 0.0) {
            println("Overall balance must be 0, but was $balance!")
            return false
        }
        return true
    }

    fun getNextSource(): Vertex? {
        if (sources.isEmpty()) {
            return null
        }
        return sources.remove(sources.keys.first())!!
    }

    fun isSuperSourceBalanced(): Boolean {
        if (getSuperSource()!!.getBalance() - getSuperSource()!!.getOutgoingFlow() != 0.0) {
            println("Supersource not balanced: ${getSuperSource()} with outgoing flow = ${getSuperSource()!!.getOutgoingFlow()}")
            return false
        }
        return true
    }

    fun isBalanced(): Boolean {
        sources.clear()
        sinks.clear()
        for (vertex in vertices.values) {
            val actualBalance = vertex.getActualBalance()
            if (actualBalance < 0) {
                sinks[vertex.getId()] = vertex
            }
            if (actualBalance > 0) {
                sources[vertex.getId()] = vertex
            }
        }
        if (sources.isNotEmpty()) {
            return false
        }

        return sinks.isEmpty()
    }

    fun getFlowCost(): Double {
        var cost = 0.0
        for (edge in edges) {
            if (!edge.residual)
                cost += edge.cost * edge.flow
        }
        return cost
    }

    fun getSuperSource(): Vertex? {
        return superSource
    }

    fun getSuperSink(): Vertex? {
        return superSink
    }

    fun getSuperNode(): Vertex? {
        return superNode
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

    fun createSuperNode() {
        superNode = Vertex(vertices.size)
        vertices[superNode!!.getId()] = superNode!!
        // Supernode, SuperSource and SuperSink will be ignored
        for (i in 0 until vertices.size - 3) {
            connectVertices(Edge(superNode!!, vertices[i]!!, 1.0))
        }
    }

    fun createSuperSource(): Int {
        if (superSource != null) {
            return superSource!!.getId()
        }
        superSource = Vertex(vertices.size)
        for (source in sources.values) {
            val vertex = vertices[source.getId()]!!
            val edge = Edge(superSource!!, vertex, abs(vertex.getBalance()), 0.0)
            this.connectVertices(edge)
            superSource!!.addBalance(vertex.getBalance())
            vertex.addBalance(-vertex.getBalance())
        }
        sources.clear()
        return superSource!!.getId()
    }

    fun createSuperSink(): Int {
        if (superSink != null) {
            return superSink!!.getId()
        }
        superSink = Vertex(vertices.size)
        for (sink in sinks.values) {
            val vertex = vertices[sink.getId()]!!
            val edge = Edge(vertex, superSink!!, abs(vertex.getBalance()), 0.0)
            this.connectVertices(edge)
            superSink!!.addBalance(vertex.getBalance())
            vertex.addBalance(-vertex.getBalance())
        }
        sinks.clear()
        return superSink!!.getId()
    }

}