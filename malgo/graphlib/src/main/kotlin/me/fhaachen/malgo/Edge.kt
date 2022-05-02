package me.fhaachen.malgo

import java.util.*

class Edge(val source: Vertex, val target: Vertex, val capacity: Double?) : Comparable<Edge> {
    constructor(source: Vertex, target: Vertex) : this(
        source, target, null
    )

    constructor(source: Vertex, target: Vertex, capacity: String) : this(
        source, target, capacity.toDouble()
    )

    override fun compareTo(other: Edge): Int {
        return Objects.compare(this, other, compareBy(Edge::capacity))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Edge

        if (source != other.source) return false
        if (target != other.target) return false
        if (capacity != other.capacity) return false

        return true
    }

    override fun hashCode(): Int {
        var result = source.hashCode()
        result = 31 * result + target.hashCode()
        result = 31 * result + (capacity?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Edge(source=$source, target=$target, capacity=$capacity)"
    }

}