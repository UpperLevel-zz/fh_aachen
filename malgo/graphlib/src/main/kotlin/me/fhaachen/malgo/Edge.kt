package me.fhaachen.malgo

import java.util.*

class Edge(
    var source: Vertex,
    var target: Vertex,
    var capacity: Double,
    var cost: Double,
    var flow: Double,
    var residual: Boolean
) : Comparable<Edge> {
    constructor(source: Vertex, target: Vertex) : this(
        source, target, 0.0, 0.0, 0.0, false
    )

    constructor(source: Vertex, target: Vertex, capacity: String) : this(
        source, target, capacity.toDouble(), 0.0, 0.0, false
    )

    constructor(source: Vertex, target: Vertex, capacity: Double) : this(
        source, target, capacity, 0.0, 0.0, false
    )

    constructor(source: Vertex, target: Vertex, capacity: Double, cost: Double) : this(
        source, target, capacity, cost, 0.0, false
    )

    fun updateFlow(flow: Double) {
        this.capacity += flow
        this.flow -= flow
    }

    override fun compareTo(other: Edge): Int {
        return Objects.compare(this, other, compareBy(Edge::capacity))
    }


    override fun toString(): String {
        return "Edge(source=$source, target=$target, capacity=$capacity, cost=$cost, flow=$flow, residual=$residual)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Edge

        if (source != other.source) return false
        if (target != other.target) return false
        if (capacity != other.capacity) return false
        if (cost != other.cost) return false
        if (flow != other.flow) return false
        if (residual != other.residual) return false

        return true
    }

    override fun hashCode(): Int {
        var result = source.hashCode()
        result = 31 * result + target.hashCode()
        result = 31 * result + capacity.hashCode()
        result = 31 * result + cost.hashCode()
        result = 31 * result + flow.hashCode()
        result = 31 * result + residual.hashCode()
        return result
    }

}