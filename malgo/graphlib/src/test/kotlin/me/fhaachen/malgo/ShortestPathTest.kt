package me.fhaachen.malgo

import org.assertj.core.api.Assertions
import org.assertj.core.data.Percentage
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream
import kotlin.test.Ignore

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ShortestPathTest {

    @ParameterizedTest
    @ValueSource(
        strings = [
            "Wege1.txt",
            "Wege2.txt",
            "Wege3.txt"
        ]
    )
    internal fun dijkstra(resourceName: String) {
        val graph = GraphTest.toDiGraph(resourceName)
        val shortestPathTree = ShortestPath.dijkstra(graph, 2)
        for (shortestPathElement in shortestPathTree) {
            println(shortestPathElement)
        }
    }

    @Test
    internal fun dijkstra_G_1_2() {
        val resourceName = "G_1_2.txt"
        val digraph = GraphTest.toDiGraph(resourceName)
        var dijkstra = ShortestPath.dijkstra(digraph, 0)
        println(dijkstra[1])

        val graph = GraphTest.toGraph(resourceName)
        dijkstra = ShortestPath.dijkstra(graph, 0)
        println(dijkstra[1])
    }

    @ParameterizedTest
    @MethodSource("testResults")
    internal fun mooreBellmanFord(resourceName: String, from: Int, to: Int, result: Double) {
        val graph = GraphTest.toDiGraph(resourceName)
        val shortestPathResult = ShortestPath.mooreBellmanFord(graph, from)
        for (shortestPathElement in shortestPathResult.shortestPathTree) {
            if (shortestPathElement.vertexId == to) {
                Assertions.assertThat(shortestPathElement.distance)
                    .isCloseTo(result, Percentage.withPercentage(0.000001))
            }
        }
    }

    @Test
    internal fun mooreBellmanFord_findCycle() {
        val resourceName = "Wege3.txt"
        val graph = GraphTest.toDiGraph(resourceName)
        val shortestPathResult = ShortestPath.mooreBellmanFord(graph, 2)
        Assertions.assertThat(shortestPathResult.cycle).isNotNull
    }

    @Ignore
    @Test
    internal fun mooreBellmanFord_G_1_2_as_undirected_graph() {
        val resourceName = "G_1_2.txt"
        val graph = GraphTest.toGraph(resourceName)
        val shortestPathResult = ShortestPath.mooreBellmanFord(graph, 0)
        for (shortestPathElement in shortestPathResult.shortestPathTree) {
            if (shortestPathElement.vertexId == 1) {
                Assertions.assertThat(shortestPathElement.distance)
                    .isCloseTo(2.36802, Percentage.withPercentage(0.000001))
            }
        }
    }

    private fun testResults(): Stream<Arguments?>? {
        return Stream.of(
            Arguments.of("Wege1.txt", 2, 0, 6.0),
            Arguments.of("Wege2.txt", 2, 0, 2.0),
            Arguments.of("G_1_2.txt", 0, 1, 5.56283)
        )
    }

}