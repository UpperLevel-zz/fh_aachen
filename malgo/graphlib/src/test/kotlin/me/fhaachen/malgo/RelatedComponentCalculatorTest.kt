package me.fhaachen.malgo

import me.fhaachen.malgo.GraphTest.Companion.toGraph
import me.fhaachen.malgo.GraphTest.Companion.undirectedGraph
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RelatedComponentCalculatorTest {

    @Test
    fun only_isolated_vertices_BFS() {
        val V = 10;
        val graph = UndirectedGraph(V)
        assertThat(RelatedComponentCalculator.breadthFirstSearch(graph)).isEqualTo(V)
    }

    @Test
    fun only_isolated_vertices_DFS() {
        val V = 10;
        val graph = UndirectedGraph(V)
        assertThat(RelatedComponentCalculator.depthFirstSearch(graph)).isEqualTo(V)
    }

    @Test
    fun only_isolated_vertices_DFS_recursive() {
        val V = 10;
        val graph = UndirectedGraph(V)
        assertThat(RelatedComponentCalculator.depthFirstSearchRecursive(graph)).isEqualTo(V)
    }

    @Test
    fun sling_BFS() {
        val graph = UndirectedGraph(1)
        graph.connectVertices(Vertex(0), Vertex(0))
        assertThat(RelatedComponentCalculator.breadthFirstSearch(graph)).isEqualTo(1)
    }

    @Test
    fun sling_DFS() {
        val graph = UndirectedGraph(1)
        graph.connectVertices(Vertex(0), Vertex(0))
        assertThat(RelatedComponentCalculator.depthFirstSearch(graph)).isEqualTo(1)
    }

    @Test
    fun sling_DFS_recursive() {
        val graph = UndirectedGraph(1)
        graph.connectVertices(Vertex(0), Vertex(0))
        assertThat(RelatedComponentCalculator.depthFirstSearchRecursive(graph)).isEqualTo(1)
    }

    @Test
    fun one_sling_one_isolated_BFS() {
        val graph = UndirectedGraph(2)
        graph.connectVertices(Vertex(0), Vertex(0))
        assertThat(RelatedComponentCalculator.breadthFirstSearch(graph)).isEqualTo(2)
    }

    @Test
    fun one_sling_one_isolated_DFS() {
        val graph = UndirectedGraph(2)
        graph.connectVertices(Vertex(0), Vertex(0))
        assertThat(RelatedComponentCalculator.depthFirstSearch(graph)).isEqualTo(2)
    }

    @Test
    fun one_sling_one_isolated_DFS_recursive() {
        val graph = UndirectedGraph(2)
        graph.connectVertices(Vertex(0), Vertex(0))
        assertThat(RelatedComponentCalculator.depthFirstSearchRecursive(graph)).isEqualTo(2)
    }

    @Test
    fun one_edge_two_vertices_BFS() {
        val graph = UndirectedGraph(2)
        graph.connectVertices(Vertex(0), Vertex(1))
        assertThat(RelatedComponentCalculator.breadthFirstSearch(graph)).isEqualTo(1)
    }

    @Test
    fun one_edge_two_vertices_DFS() {
        val graph = UndirectedGraph(2)
        graph.connectVertices(Vertex(0), Vertex(1))
        assertThat(RelatedComponentCalculator.depthFirstSearch(graph)).isEqualTo(1)
    }

    @Test
    fun one_edge_two_vertices_DFS_recursive() {
        val graph = UndirectedGraph(2)
        graph.connectVertices(Vertex(0), Vertex(1))
        assertThat(RelatedComponentCalculator.depthFirstSearchRecursive(graph)).isEqualTo(1)
    }

    @Test
    fun Graph1() {
        val resourceName = "Graph1.txt"
        val graph = toGraph(resourceName)
        assertThat(graph.getEdgeCount()).isEqualTo(17)
        assert(graph, 2, 1000)
    }

    @Test
    fun Graph2() {
        val resourceName = "Graph2.txt"
        val graph = toGraph(resourceName)
        assertThat(graph.getEdgeCount()).isEqualTo(3000)
        assert(graph, 4, 1000)
    }

    @Test
    fun Graph3() {
        val resourceName = "Graph3.txt"
        val graph = toGraph(resourceName)
        assertThat(graph.getEdgeCount()).isEqualTo(2501)
        assert(graph, 4, 1000)
    }

    @Test
    fun Graph_gross() {
        val resourceName = "Graph_gross.txt"
        val graph = toGraph(resourceName)
        assertThat(graph.getEdgeCount()).isEqualTo(300000)
        assert(graph, 222, 10000)
    }

    @Test
    fun Graph_ganzgross() {
        val resourceName = "Graph_ganzgross.txt"
        val graph = toGraph(resourceName)
        assertThat(graph.getEdgeCount()).isEqualTo(1000000)
        assert(graph, 9560, 60000)
    }

    @Test
    fun Graph_ganzganzgross() {
        val resourceName = "Graph_ganzganzgross.txt"
        val graph = toGraph(resourceName)
        assertThat(graph.getEdgeCount()).isEqualTo(4000000)
        assert(graph, 306, 60000)
    }

    private fun assert(graph: Graph, zuKomp: Int, time: Long?) {
        var start = System.currentTimeMillis()
        assertThat(RelatedComponentCalculator.breadthFirstSearch(graph)).isEqualTo(zuKomp)
        var end = System.currentTimeMillis()
        println("Calc time BFS= " + (end - start))
        start = System.currentTimeMillis()
        assertThat(RelatedComponentCalculator.depthFirstSearch(graph)).isEqualTo(zuKomp)
        end = System.currentTimeMillis()
        println("Calc time DFS= " + (end - start))
        if (time != null) {
            assertThat(end - start).isLessThan(time)
        }
    }

}