package me.fhaachen.malgo

class Vertex(private val id: Int) {
    constructor(id: String) : this(
        id.toInt()
    )

    private val adjacentVertices = HashSet<Vertex>()
    val outgoingEdges = ArrayList<Edge>()
    private val incomingEdges = ArrayList<Edge>()
    private val edges = HashMap<Int, Edge>()

    fun addEdge(edge: Edge): Boolean {
        edge.target.adjacentVertices.add(this)
        edge.source.adjacentVertices.add(edge.target)
        val reversedEdge = Edge(edge.target, edge.source, capacity = edge.capacity)
        edge.target.incomingEdges.add(edge)
        edge.target.outgoingEdges.add(reversedEdge)
        edge.target.edges[edge.source.getId()] = edge
        incomingEdges.add(reversedEdge)
        outgoingEdges.add(edge)
        edges[edge.target.getId()] = edge
        return true
    }

    fun getEdges(): MutableSet<Edge> {
        return HashSet(outgoingEdges)
    }

    fun getEdge(targetId: Int): Edge {
        return edges[targetId]!!
    }

    fun getAdjacentVertices(): MutableSet<Vertex> {
        return adjacentVertices
    }

    fun getId(): Int {
        return id
    }

    override fun toString(): String {
        return "Vertex(id='$id')"
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

}