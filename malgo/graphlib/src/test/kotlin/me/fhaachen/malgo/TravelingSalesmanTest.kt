package me.fhaachen.malgo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class TravelingSalesmanTest {

    @ParameterizedTest
    @ValueSource(
        strings = [
            "K_10.txt",
            "K_10e.txt",
            "K_12.txt",
            "K_12e.txt",
            "K_15.txt",
            "K_15e.txt",
            "K_20.txt",
            "K_30.txt",
            "K_50.txt",
            "K_100.txt"
        ]
    )
    fun nearestNeighbor(resourceName: String) {
        val graph = GraphTest.toGraph(resourceName)
        for (currentVertex in graph.getVertices()) {
            val start = System.nanoTime()
            val hamiltonianCycle = TravelingSalesman.nearestNeighbor(graph, currentVertex)
            val end = System.nanoTime()
            assertThat(hamiltonianCycle.getVertexCount()).isEqualTo(graph.getVertexCount())
            assertThat(hamiltonianCycle.getEdgeCount()).isEqualTo(graph.getVertexCount())
            println(
                GraphTest.getFormattedOutput(
                    resourceName,
                    currentVertex,
                    object {}.javaClass.enclosingMethod.name,
                    end,
                    start,
                    hamiltonianCycle.capacity()
                )
            )
            println(hamiltonianCycle)
        }
        println("______________________________________")
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "K_10.txt",
            "K_10e.txt",
            "K_12.txt",
            "K_12e.txt",
            "K_15.txt",
            "K_15e.txt",
            "K_20.txt",
            "K_30.txt",
            "K_50.txt",
            "K_100.txt"
        ]
    )
    fun doppelterBaum(resourceName: String) {
        val graph = GraphTest.toGraph(resourceName)
        for (currentVertex in graph.getVertices()) {
            val start = System.nanoTime()
            val hamiltonianCycle = TravelingSalesman.doppelterBaum(graph, currentVertex)
            val end = System.nanoTime()
            assertThat(hamiltonianCycle.getVertexCount()).isEqualTo(graph.getVertexCount())
            assertThat(hamiltonianCycle.getEdgeCount()).isEqualTo(graph.getVertexCount())
            println(
                GraphTest.getFormattedOutput(
                    resourceName,
                    currentVertex,
                    object {}.javaClass.enclosingMethod.name,
                    end,
                    start,
                    hamiltonianCycle.capacity()
                )
            )
        }
        println("______________________________________")
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "K_10.txt",
            "K_10e.txt",
        ]
    )
    fun bruteForce(resourceName: String) {
        val graph = GraphTest.toGraph(resourceName)
        val start = System.nanoTime()
        val hamiltonianCycle = TravelingSalesman.bruteForce(graph)
        val end = System.nanoTime()
        assertThat(hamiltonianCycle.getVertexCount()).isEqualTo(graph.getVertexCount())
        assertThat(hamiltonianCycle.getEdgeCount()).isEqualTo(graph.getVertexCount())
        println(
            GraphTest.getFormattedOutput(
                resourceName,
                null,
                object {}.javaClass.enclosingMethod.name,
                end,
                start,
                hamiltonianCycle.capacity()
            )
        )
        println(hamiltonianCycle)
        println()
    }

}