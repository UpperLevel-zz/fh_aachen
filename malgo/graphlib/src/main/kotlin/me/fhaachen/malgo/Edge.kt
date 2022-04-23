package main.kotlin.me.fhaachen.malgo

import me.fhaachen.malgo.Vertex

class Edge(val source: Vertex, val target: Vertex, val capacity: Double?) {
    constructor(source: Vertex, target: Vertex) : this(
        source, target, null
    )

    constructor(source: Vertex, target: Vertex, capacity: String) : this(
        source, target, capacity.toDouble()
    )
}