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
        val start = System.currentTimeMillis()
        val hamiltonianCycle = TravelingSalesman.nearestNeighbor(graph)
        val end = System.currentTimeMillis()
        print("Graph in: $resourceName\t${object {}.javaClass.enclosingMethod.name}: Calc time= " + (end - start))
        assertThat(hamiltonianCycle.getVertexCount()).isEqualTo(graph.getVertexCount())
        assertThat(hamiltonianCycle.getEdgeCount()).isEqualTo(graph.getVertexCount() - 1)
        var amount = 0.0
        for (edge in hamiltonianCycle.getEdges()) {
            amount += edge.capacity!!
        }
        println("\tCapacity: $amount")
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
        print("Graph in: $resourceName\t${object {}.javaClass.enclosingMethod.name}: Calc time= " + (end - start))
        assertThat(hamiltonianCycle.getVertexCount()).isEqualTo(graph.getVertexCount())
        assertThat(hamiltonianCycle.getEdgeCount()).isEqualTo(graph.getVertexCount() - 1)
        var amount = 0.0
        for (edge in hamiltonianCycle.getEdges()) {
            amount += edge.capacity!!
        }
        println("\tCapacity: $amount")
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
    fun bruteForce(resourceName: String) {
        val graph = GraphTest.toGraph(resourceName)
        val start = System.currentTimeMillis()
        val hamiltonianCycle = TravelingSalesman.bruteForce(graph)
        val end = System.currentTimeMillis()
        print("Graph in: $resourceName\t${object {}.javaClass.enclosingMethod.name}: Calc time= " + (end - start))
        assertThat(hamiltonianCycle.getVertexCount()).isEqualTo(graph.getVertexCount())
        assertThat(hamiltonianCycle.getEdgeCount()).isEqualTo(graph.getVertexCount() - 1)
        var amount = 0.0
        for (edge in hamiltonianCycle.getEdges()) {
            amount += edge.capacity!!
        }
        println("\tCapacity: $amount")
    }

}