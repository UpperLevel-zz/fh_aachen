package me.fhaachen.malgo

import main.kotlin.me.fhaachen.malgo.Edge
import java.util.*

class MinimumSpanningTree : Graph, Tree {

    val edges = HashSet<Edge>()
    val vertices = LinkedHashSet<Vertex>()

    companion object {
        fun prim(graph: Graph): MinimumSpanningTree {
            val mst = MinimumSpanningTree()
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

        fun kruskal(graph: Graph): MinimumSpanningTree {
            val mst = MinimumSpanningTree()
            val map = HashMap<Int, Collection<Vertex>>()
            val visited = IntArray(graph.getVertexCount())
            for (vertex in graph.getVertices()) {
                map[vertex.getId()] = hashSetOf(vertex)
                //todo eventuell eine verkettete Liste nehmen
                visited[vertex.getId()] = vertex.getId()
            }
            val edges = graph.getEdges().sortedBy { it.capacity }
            for (edge in edges) {
                if (mst.getEdgeCount() == graph.getVertexCount() - 1) {
                    break
                }
                val sourceId = edge.source.getId()
                val targetId = edge.target.getId()
                val s = map[visited[sourceId]] as HashSet<Vertex>
                val t = map[visited[targetId]] as HashSet<Vertex>
                if (s != t) {
                    mst.connectVertices(edge)
                    if (s.size < t.size) {
                        t.addAll(s)
                        map[sourceId] = t
                        s.forEach { vertex -> visited[vertex.getId()] = sourceId }
                    } else {
                        s.addAll(t)
                        map[sourceId] = s
                        t.forEach { vertex -> visited[vertex.getId()] = sourceId }
                    }
                }
            }
            return mst
        }

        private fun addVertexAndNextEdges(
            mst: MinimumSpanningTree,
            currentEdge: Edge,
            vertex: Vertex,
            connected: BooleanArray,
            priorityQueue: PriorityQueue<Edge>
        ) {
            mst.connectVertices(currentEdge)
            connected[vertex.getId()] = true
            vertex.getEdges().forEach { edge ->
                if (!connected[edge.source.getId()] || !connected[edge.target.getId()]) priorityQueue.add(edge)
            }
        }
    }

    override fun connectVertices(edge: Edge) {
        edges.add(edge)
        vertices.add(edge.source)
        vertices.add(edge.target)
    }

    override fun getIds(): LinkedList<Int> {
        return LinkedList(vertices.stream().map { v -> v.getId() }.toList())
    }

    override fun getVertices(): LinkedList<Vertex> {
        return LinkedList(vertices)
    }

    override fun getVertex(id: Int): Vertex {
        return vertices.find { v -> v.getId() == id }!!
    }

    override fun getEdgeCount(): Int {
        return edges.size
    }

    override fun getVertexCount(): Int {
        return vertices.size
    }

    override fun getEdges(): LinkedList<Edge> {
        return LinkedList(edges)
    }

    override fun isEmpty(): Boolean {
        return edges.isEmpty()
    }
}