package me.fhaachen.malgo

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class HamiltonianTourTest {

    @ParameterizedTest
    @ValueSource(
        strings = [
            "K_10.txt",
            "K_10e.txt",
            "K_12.txt"
        ]
    )
    fun findMostFavorable(resourceName: String) {
        val graph = GraphTest.toGraph(resourceName)
        val start = System.nanoTime()
        val hamiltonianTour = HamiltonianTour()
        hamiltonianTour.findMostFavorable(graph, false)
        val end = System.nanoTime()
        println(
            GraphTest.getFormattedOutput(
                resourceName,
                null,
                object {}.javaClass.enclosingMethod.name,
                end,
                start,
                hamiltonianTour.lowestAmount
            )
        )
        println(hamiltonianTour.optimalCycle)
        println()
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "K_10.txt",
            "K_10e.txt",
            "K_12.txt"
        ]
    )
    fun findMostFavorable_with_bound(resourceName: String) {
        val graph = GraphTest.toGraph(resourceName)
        val start = System.nanoTime()
        val hamiltonianTour = HamiltonianTour()
        hamiltonianTour.findMostFavorable(graph, true)
        val end = System.nanoTime()
        println(
            GraphTest.getFormattedOutput(
                resourceName,
                null,
                object {}.javaClass.enclosingMethod.name,
                end,
                start,
                hamiltonianTour.lowestAmount
            )
        )
        println(hamiltonianTour.optimalCycle)
        println()
    }
}