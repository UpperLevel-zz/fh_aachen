package me.fhaachen.malgo

import java.util.*

class MinimumSpanningTree : Graph {

    private val edges = LinkedList<Edge>()
    private var vertices: HashMap<Int, Vertex> = HashMap()

    companion object {
        fun prim(graph: Graph): MinimumSpanningTree {
            return prim(graph, graph.getVertices().first)
        }

        fun prim(graph: Graph, startId: Int): MinimumSpanningTree {
            val mst = MinimumSpanningTree()
            val priorityQueue = PriorityQueue<Edge>(compareBy { it.capacity })
            val connected = BooleanArray(graph.getIds().size)
            var currentEdge: Edge
            graph.getVertex(startId).getEdges().forEach { edge -> priorityQueue.add(edge) }
            connected[startId] = true
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
            val map = HashMap<Int, Collection<Int>>()
            val visited = IntArray(graph.getVertexCount())
            for (id in graph.getVertices()) {
                map[id] = hashSetOf(id)
                visited[id] = id
            }
            val edges = graph.getEdges().sortedBy { it.capacity }
            for (edge in edges) {
                if (mst.getEdgeCount() == graph.getVertexCount() - 1) {
                    break
                }
                val sourceId = edge.source.getId()
                val targetId = edge.target.getId()
                val s = map[visited[sourceId]] as HashSet<Int>
                val t = map[visited[targetId]] as HashSet<Int>
                if (s != t) {
                    mst.connectVertices(edge)
                    if (s.size < t.size) {
                        t.addAll(s)
                        map[sourceId] = t
                        s.forEach { id -> visited[id] = sourceId }
                    } else {
                        s.addAll(t)
                        map[sourceId] = s
                        t.forEach { id -> visited[id] = sourceId }
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
        val source = vertices.getOrDefault(edge.source.getId(), Vertex(edge.source.getId()))
        vertices[source.getId()] = source
        val target = vertices.getOrDefault(edge.target.getId(), Vertex(edge.target.getId()))
        vertices[target.getId()] = target
        val edgeCopy = Edge(source, target, edge.capacity, edge.cost)
        source.addOutgoingEdge(edgeCopy)
        edges.add(edgeCopy)
    }

    override fun getIds(): LinkedList<Int> {
        return LinkedList(vertices.keys)
    }

    override fun getVertices(): LinkedList<Int> {
        return LinkedList(vertices.keys)
    }

    override fun getVertex(id: Int): Vertex {
        return vertices[id]!!
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

    override fun toAdjacentCapacities(): Array<DoubleArray> {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "MinimumSpanningTree(edges=$edges)"
    }

}