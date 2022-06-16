package me.fhaachen.malgo

class Vertex(private val id: Int, private var balance: Double) {
    constructor(id: String) : this(
        id.toInt(), 0.0
    )

    constructor(id: Int) : this(
        id, 0.0
    )

    private val adjacentVertices = HashMap<Int, Vertex>()
    val outgoingEdges = HashMap<Int, Edge>()
    private val incomingEdges = HashMap<Int, Edge>()
    var pseudoBalance = 0.0

    fun addOutgoingEdge(edge: Edge) {
        this.adjacentVertices[edge.target.getId()] = edge.target
        outgoingEdges[edge.target.getId()] = edge
    }

    fun addIncomingEdge(edge: Edge) {
        this.adjacentVertices[edge.source.getId()] = edge.source
        incomingEdges[edge.source.getId()] = edge
    }

    fun getEdges(): MutableSet<Edge> {
        return HashSet(outgoingEdges.values)
    }

    fun getOutgoingEdge(targetId: Int): Edge {
        return outgoingEdges[targetId]!!
    }

    fun hasOutgoingEdge(targetId: Int): Boolean {
        return outgoingEdges.containsKey(targetId)
    }

    fun getActualBalance(): Double {
        return getIncomingFlow() + getOutgoingFlow()
    }

    fun getOutgoingFlow(): Double {
        var flowOut = 0.0
        for (outgoingEdge in outgoingEdges.values) {
            flowOut += outgoingEdge.flow
        }
        return flowOut
    }

    fun getIncomingFlow(): Double {
        var flowIn = 0.0
        for (incomingEdge in incomingEdges.values) {
            flowIn += incomingEdge.flow
        }
        return flowIn
    }

    fun getAdjacentVertices(): MutableSet<Int> {
        return adjacentVertices.keys
    }

    fun getId(): Int {
        return id
    }

    fun getBalance(): Double {
        return balance
    }

    fun addBalance(other: Double): Double {
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