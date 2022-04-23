package me.fhaachen.malgo

import main.kotlin.me.fhaachen.malgo.Edge

class Vertex(private val id: Int) {
    constructor(id: String) : this(
        id.toInt()
    )

    val adjacentVertices = HashSet<Vertex>()
    val edges = HashSet<Edge>()

    fun addEdge(edge: Edge): Boolean {
        if (edge.source != this) {
            return false
        }
        edge.target.adjacentVertices.add(this)
        edge.source.adjacentVertices.add(edge.target)
        return edges.add(edge)
    }

    fun getEdges(): MutableSet<Edge> {
        return edges
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