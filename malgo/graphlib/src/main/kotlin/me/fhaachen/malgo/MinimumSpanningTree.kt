package me.fhaachen.malgo

import main.kotlin.me.fhaachen.malgo.Edge
import java.util.*

class MinimumSpanningTree {
    companion object {
        fun prim(graph: Graph): HashSet<Edge> {
            val mst = HashSet<Edge>()
            val capacityWeighted: Comparator<Edge> = compareBy { it.capacity }
            //todo knotenbasierte queue anschauen
            val priorityQueue = PriorityQueue(capacityWeighted)
            val connected = BooleanArray(graph.getIds().size)
            val startVertex = graph.getVertices().first
            var currentEdge: Edge
            startVertex.getEdges().forEach { edge -> priorityQueue.add(edge) }
            connected[startVertex.getId()] = true
            while (!priorityQueue.isEmpty()) {
                currentEdge = priorityQueue.poll()
                if (!connected[currentEdge.target.getId()]) {
                    addVertexAndNextEdges(mst, currentEdge, currentEdge.target, connected, priorityQueue)
                } else if (!connected[currentEdge.source.getId()]) {
                    addVertexAndNextEdges(mst, currentEdge, currentEdge.source, connected, priorityQueue)
                }
            }
            return mst
        }

        fun kruskal(graph: Graph): HashSet<Edge> {
            val mst = HashSet<Edge>()
            val map = HashMap<Int, List<Vertex>>()
            val visited = IntArray(graph.size())
            for (vertex in graph.getVertices()) {
                map[vertex.getId()] = arrayListOf(vertex)
                //todo eventuell eine verkettete Liste nehmen
                visited[vertex.getId()] = vertex.getId()
            }
            val edges = graph.getEdges().sortedBy { it.capacity }
            for (edge in edges) {
                if (mst.size == graph.size() - 1) {
                    break
                }
                val sourceId = edge.source.getId()
                val targetId = edge.target.getId()
                val s = map[visited[sourceId]] as ArrayList<Vertex>
                val t = map[visited[targetId]] as ArrayList<Vertex>
                //todo größen der beachten
                if (s != t) {
                    mst.add(edge)
                    if (s.size < t.size) {
                        s.addAll(t)
                        map[sourceId] = s
                        t.forEach { vertex -> visited[vertex.getId()] = sourceId }
                    } else {
                        t.addAll(s)
                        map[sourceId] = t
                        s.forEach { vertex -> visited[vertex.getId()] = sourceId }
                    }
//                    visited[sourceId] = sourceId
//                    visited[targetId] = sourceId
                }
            }
            return mst
        }

        private fun addVertexAndNextEdges(
            mst: HashSet<Edge>,
            currentEdge: Edge,
            vertex: Vertex,
            connected: BooleanArray,
            priorityQueue: PriorityQueue<Edge>
        ) {
            mst.add(currentEdge)
            connected[vertex.getId()] = true
            //todo edges herauslassen, zu knoten die bereits besucht wurden
            vertex.getEdges().forEach { edge -> priorityQueue.add(edge) }
        }
    }
}