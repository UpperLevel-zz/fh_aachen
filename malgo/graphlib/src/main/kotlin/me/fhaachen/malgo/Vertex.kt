package me.fhaachen.malgo

import main.kotlin.me.fhaachen.malgo.Edge

class Vertex(private val id: Int) {
    constructor(id: String) : this(
        id.toInt()
    )

    val adjacentVertices = HashSet<Vertex>()
    val outgoingEdges = ArrayList<Edge>()
    val incomingEdges = ArrayList<Edge>()

    fun addEdge(edge: Edge): Boolean {
        edge.target.adjacentVertices.add(this)
        edge.source.adjacentVertices.add(edge.target)
        val reversedEdge = Edge(edge.target, edge.source, capacity = edge.capacity)
        edge.target.incomingEdges.add(edge)
        edge.target.outgoingEdges.add(reversedEdge)
        incomingEdges.add(reversedEdge)
        return outgoingEdges.add(edge)
    }

    fun getEdges(): MutableSet<Edge> {
        return HashSet(outgoingEdges)
    }

    fun getEdge(targetId: Int): Edge? {
        return try {
            outgoingEdges.stream().filter { e -> e.target.getId() == targetId }.findFirst().get()
        } catch (e: NoSuchElementException) {
//            println("Vertex.getEdge: Previous ${this.getId()}")
//            println("Vertex.getEdge: Current $targetId")
            null
        }
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