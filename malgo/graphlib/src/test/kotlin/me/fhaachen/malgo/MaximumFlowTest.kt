package me.fhaachen.malgo

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class MaximumFlowTest {

    @Test
    internal fun fordFulkerson_G_1_2() {
        val resourceName = "G_1_2.txt"
        val graph = GraphTest.toDiGraph(resourceName)
        val maximumFlow = MaxFlow.fordFulkerson(graph, 0, 7)
        println(maximumFlow)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "Fluss.txt",
            "Fluss2.txt"
        ]
    )
    internal fun fordFulkerson(resourceName: String) {
        val graph = GraphTest.toDiGraph(resourceName)
        val maximumFlow = MaxFlow.fordFulkerson(graph, 0, 7)
        println(maximumFlow)
    }
}