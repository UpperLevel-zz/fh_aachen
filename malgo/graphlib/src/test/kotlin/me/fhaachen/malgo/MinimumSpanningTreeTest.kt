package me.fhaachen.malgo

import main.kotlin.me.fhaachen.malgo.Edge
import org.assertj.core.api.Assertions
import org.assertj.core.data.Percentage
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MinimumSpanningTreeTest {

    @Test
    fun simple_graph() {
        val graph = UndirectedGraph(4)
        graph.connectVertices(Edge(Vertex(0), Vertex(1), 0.7))
        graph.connectVertices(Edge(Vertex(1), Vertex(2), 0.5))
        graph.connectVertices(Edge(Vertex(1), Vertex(3), 0.6))
        graph.connectVertices(Edge(Vertex(2), Vertex(3), 0.2))
        graph.connectVertices(Edge(Vertex(3), Vertex(0), 0.2))
        assert(graph, 0.9)
    }

    @Test
    fun simple_graph_no_circle() {
        val graph = UndirectedGraph(5)
        graph.connectVertices(Edge(Vertex(2), Vertex(3), 0.1))
        graph.connectVertices(Edge(Vertex(1), Vertex(2), 0.2))
        graph.connectVertices(Edge(Vertex(0), Vertex(1), 0.3))
        graph.connectVertices(Edge(Vertex(4), Vertex(3), 0.4))
        assert(graph, 1.0)
    }

    @Test
    fun G_1_2() {
        val resourceName = "G_1_2.txt"
        val graph = GraphTest.toGraph(resourceName)
        assert(graph, 287.32286)
    }

    @Test
    fun G_1_20() {
        val resourceName = "G_1_20.txt"
        val graph = GraphTest.toGraph(resourceName)
        assert(graph, 36.86275)
    }

    @Test
    fun G_1_200() {
        val resourceName = "G_1_200.txt"
        val graph = GraphTest.toGraph(resourceName)
        assert(graph, 12.68182)
    }

    @Test
    fun G_10_20() {
        val resourceName = "G_10_20.txt"
        val graph = GraphTest.toGraph(resourceName)
        assert(graph, 2785.62417)
    }

    @Test
    fun G_10_200() {
        val resourceName = "G_10_200.txt"
        val graph = GraphTest.toGraph(resourceName)
        assert(graph, 372.14417)
    }

    @Test
    fun G_100_200() {
        val resourceName = "G_100_200.txt"
        val graph = GraphTest.toGraph(resourceName)
        assert(graph, 27550.51488)
    }

    private fun assert(graph: Graph, capacityCumm: Double) {
        var start = System.currentTimeMillis()
        var mst = MinimumSpanningTree.prim(graph)
        var end = System.currentTimeMillis()
        println("Calc time Prim= " + (end - start))
        Assertions.assertThat(mst.size).isEqualTo(graph.getIds().size - 1)
        start = System.currentTimeMillis()
        mst = MinimumSpanningTree.kruskal(graph)
        end = System.currentTimeMillis()
        println("Calc time Kruskal= " + (end - start))
        Assertions.assertThat(mst.size).isEqualTo(graph.getIds().size - 1)
        var cost = 0.0
        for (edge in mst) {
            cost += edge.capacity!!
        }
        Assertions.assertThat(cost).isCloseTo(capacityCumm, Percentage.withPercentage(0.000001))
    }
}