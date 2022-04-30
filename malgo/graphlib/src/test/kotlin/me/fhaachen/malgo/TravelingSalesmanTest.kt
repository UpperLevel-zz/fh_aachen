package me.fhaachen.malgo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class TravelingSalesmanTest {

    @Test
    fun G_1_2() {
        val resourceName = "G_1_2.txt"
        val graph = GraphTest.toGraph(resourceName)
        val hamiltonianCycle = TravelingSalesman.nearestNeighbor(graph)
        println(hamiltonianCycle)
        assertThat(hamiltonianCycle.getVertexCount()).isEqualTo(171)
        assertThat(hamiltonianCycle.getEdgeCount()).isEqualTo(170)
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
    fun nearestNeighbor(resourceName: String) {
        val graph = GraphTest.toGraph(resourceName)
        val start = System.currentTimeMillis()
        val hamiltonianCycle = TravelingSalesman.nearestNeighbor(graph)
        val end = System.currentTimeMillis()
        println("Calc time= " + (end - start))
        assertThat(hamiltonianCycle.getVertexCount()).isEqualTo(graph.getVertexCount())
        assertThat(hamiltonianCycle.getEdgeCount()).isEqualTo(graph.getVertexCount() - 1)
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
        val start = System.currentTimeMillis()
        val hamiltonianCycle = TravelingSalesman.doppelterBaum(graph)
        val end = System.currentTimeMillis()
        println("Calc time= " + (end - start))
        assertThat(hamiltonianCycle.getVertexCount()).isEqualTo(graph.getVertexCount())
        assertThat(hamiltonianCycle.getEdgeCount()).isEqualTo(graph.getVertexCount() - 1)
    }

}