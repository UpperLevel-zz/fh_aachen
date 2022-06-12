package me.fhaachen.malgo

class Vertex(private val id: Int, private var balance: Double) {
    constructor(id: String) : this(
        id.toInt(), 0.0
    )

    constructor(id: Int) : this(
        id, 0.0
    )

    private val adjacentVertices = HashMap<Int, Vertex>()
    val outgoingEdges = ArrayList<Edge>()
    private val targetEdgeMapping = HashMap<Int, Edge>()
    private val incomingEdges = ArrayList<Edge>()
    private val edges = HashMap<Int, Edge>()

    fun addOutgoingEdge(edge: Edge) {
        if (this.id != edge.target.getId()) {
            this.adjacentVertices[edge.target.getId()] = edge.target
        }
        outgoingEdges.add(edge)
        targetEdgeMapping[edge.target.getId()] = edge
        edges[edge.target.getId()] = edge
    }

    fun addIncomingEdge(edge: Edge) {
        if (this.id != edge.source.getId()) {
            this.adjacentVertices[edge.source.getId()] = edge.source
        }
        incomingEdges.add(edge)
        if (edges[edge.source.getId()] == null)
            edges[edge.source.getId()] = edge
    }

    fun getEdges(): MutableSet<Edge> {
        return HashSet(outgoingEdges)
    }

    fun getEdge(targetId: Int): Edge {
        return edges[targetId]!!
    }

    fun hasOutgoingEdge(targetId: Int): Boolean {
        return targetEdgeMapping.containsKey(targetId)
    }

    fun getAdjacentVertices(): MutableSet<Int> {
        return edges.keys
    }

    fun getId(): Int {
        return id
    }

    fun getBalance(): Double {
        return balance
    }

    fun updateBalance(other: Double): Double {
        balance += other
        return balance
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vertex

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }

    override fun toString(): String {
        return "Vertex(id=$id, balance=$balance)"
    }

}