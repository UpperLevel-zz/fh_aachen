package me.fhaachen.malgo

import me.fhaachen.malgo.input.MinCostFlowFormat
import org.assertj.core.api.Assertions
import org.assertj.core.data.Percentage
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.Ignore

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class MinCostFlowTest {

    @ParameterizedTest
    @MethodSource("possibleFlow")
    internal fun cycleCancelation(resourceName: String, result: Double) {
        val graph = MinCostFlowFormat.toGraph(resourceName)
        val flowResult = MinCostFlow.cycleCancelation(graph)
        Assertions.assertThat(flowResult.minCost).isCloseTo(result, Percentage.withPercentage(0.000001))
    }

    @ParameterizedTest
    @MethodSource("noBFlow")
    internal fun cycleCancelationNoBFlow(resourceName: String, result: Double?) {
        val graph = MinCostFlowFormat.toGraph(resourceName)
        val flowResult = MinCostFlow.cycleCancelation(graph)
        Assertions.assertThat(flowResult.minCost).isNull()
    }

    @Ignore
    @ParameterizedTest
    @MethodSource("possibleFlow")
    internal fun successiveShortestPath(resourceName: String, result: Double) {
        val graph = MinCostFlowFormat.toGraph(resourceName)
        val flowResult = MinCostFlow.successiveShortestPath(graph)
        Assertions.assertThat(flowResult.minCost).isCloseTo(result, Percentage.withPercentage(0.000001))
    }

    @Ignore
    @ParameterizedTest
    @MethodSource("noBFlow")
    internal fun successiveShortestPathNoBFlow(resourceName: String, result: Double?) {
        val graph = MinCostFlowFormat.toGraph(resourceName)
        val flowResult = MinCostFlow.successiveShortestPath(graph)
        Assertions.assertThat(flowResult.minCost).isNull()
    }

    private fun possibleFlow(): Stream<Arguments?>? {
        return Stream.of(
            Arguments.of("Kostenminimal1.txt", 3.0),
            Arguments.of("Kostenminimal2.txt", 0.0),
            Arguments.of("Kostenminimal_gross1.txt", 1537),
            Arguments.of("Kostenminimal_gross2.txt", 1838)
        )
    }

    private fun noBFlow(): Stream<Arguments?>? {
        return Stream.of(
            Arguments.of("Kostenminimal3.txt", null),
            Arguments.of("Kostenminimal4.txt", null),
            Arguments.of("Kostenminimal_gross3.txt", null)
        )
    }

}