package me.fhaachen.malgo

class Vertex(private val id: Int) {
    constructor(id: String) : this(
        id.toInt()
    )

    var adjacentVertices = HashSet<Vertex>()

    fun addAdjacentVertex(vertex: Vertex): Boolean {
        vertex.adjacentVertices.add(this)
        return adjacentVertices.add(vertex)
    }

    fun removeAdjacentVertex(vertex: Vertex): Boolean {
        return adjacentVertices.remove(vertex)
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