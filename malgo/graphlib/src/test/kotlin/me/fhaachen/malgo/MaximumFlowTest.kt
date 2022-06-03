package me.fhaachen.malgo

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class MaximumFlowTest {

    @Test
    internal fun edmondsKarp_G_1_2() {
        val resourceName = "G_1_2.txt"
        val graph = GraphTest.toDiGraph(resourceName)
        val maximumFlow = MaxFlow.edmondsKarp(graph, 0, 7)
        println(maximumFlow)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "Fluss.txt",
            "Fluss2.txt"
        ]
    )
    internal fun edmondsKarp(resourceName: String) {
        val graph = GraphTest.toDiGraph(resourceName)
        val maximumFlow = MaxFlow.edmondsKarp(graph, 0, 7)
        println(maximumFlow)
    }

}