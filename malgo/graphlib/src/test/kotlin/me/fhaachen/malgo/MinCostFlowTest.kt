package me.fhaachen.malgo

import me.fhaachen.malgo.input.MinCostFlowFormat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class MinCostFlowTest {

    @ParameterizedTest
    @ValueSource(
        strings = [
            "Kostenminimal1.txt",
            "Kostenminimal2.txt",
            "Kostenminimal3.txt",
            "Kostenminimal4.txt",
        ]
    )
    internal fun cycleCancelation(resourceName: String) {
        val graph = MinCostFlowFormat.toGraph(resourceName)
        val minCostFlow = MinCostFlow.cycleCancelation(graph)
        println(minCostFlow)
    }

}