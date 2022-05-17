package me.fhaachen.malgo

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

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
        var graph = GraphTest.toDiGraph(resourceName)
        var dijkstra = ShortestPath.dijkstra(graph, 0)
        println(dijkstra[1])

        graph = GraphTest.toGraph(resourceName)
        dijkstra = ShortestPath.dijkstra(graph, 0)
        println(dijkstra[1])
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "Wege1.txt",
            "Wege2.txt",
            "Wege3.txt"
        ]
    )
    internal fun mooreBellmanFord(resourceName: String) {
        val graph = GraphTest.toDiGraph(resourceName)
        val shortestPathTree = ShortestPath.mooreBellmanFord(graph, 2)
        for (shortestPathElement in shortestPathTree) {
            println(shortestPathElement)
        }
    }
}