package me.fhaachen.malgo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class MaximumFlowTest {

    @ParameterizedTest
    @MethodSource("testParameters")
    internal fun edmondsKarp(resourceName: String, result: Double) {
        val graph = GraphTest.toDiGraph(resourceName)
        val maximumFlow = MaxFlow.edmondsKarp(graph, 0, 7)
        assertThat(maximumFlow).isEqualTo(result)
        println(maximumFlow)
    }

    private fun testParameters(): Stream<Arguments?>? {
        return Stream.of(
            Arguments.of("Fluss.txt", 4.0),
            Arguments.of("Fluss2.txt", 5.0),
            Arguments.of("G_1_2.txt", 0.75447)
        )
    }

}