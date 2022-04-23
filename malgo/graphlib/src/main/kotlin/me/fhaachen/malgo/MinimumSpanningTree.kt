package me.fhaachen.malgo

import main.kotlin.me.fhaachen.malgo.Edge
import java.util.*

class MinimumSpanningTree {
    companion object {
        fun prim(graph: Graph): HashSet<Vertex> {
            val mst = HashSet<Vertex>()
            val capacityWeighted: Comparator<Edge> = compareBy { it.capacity }
            val priorityQueue = PriorityQueue(capacityWeighted)
            val vertices = graph.getVertices()
            val connected = BooleanArray(graph.getIds().size)
            val startVertex = vertices.removeFirst()
            var currentEdge: Edge
            mst.add(startVertex)
            startVertex.getEdges().forEach { edge -> priorityQueue.add(edge) }
            connected[startVertex.getId()] = true
            while (!priorityQueue.isEmpty()) {
                currentEdge = priorityQueue.poll()
                if (!connected[currentEdge.target.getId()]) {
                    mst.add(currentEdge.target)
                    connected[currentEdge.target.getId()] = true
                    currentEdge.target.getEdges().forEach { edge -> priorityQueue.add(edge) }
                }
            }
            return mst
        }
    }
}