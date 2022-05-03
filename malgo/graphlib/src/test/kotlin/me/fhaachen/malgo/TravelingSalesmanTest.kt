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
            var amount = 0.0
            for (edge in hamiltonianCycle.getEdges()) {
                amount += edge.capacity!!
            }
            println(
                getFormattedOutput(
                    resourceName,
                    currentVertex,
                    object {}.javaClass.enclosingMethod.name,
                    end,
                    start,
                    amount
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
            val start = System.currentTimeMillis()
            val hamiltonianCycle = TravelingSalesman.doppelterBaum(graph, currentVertex)
            val end = System.currentTimeMillis()
            assertThat(hamiltonianCycle.getVertexCount()).isEqualTo(graph.getVertexCount())
            assertThat(hamiltonianCycle.getEdgeCount()).isEqualTo(graph.getVertexCount())
            var amount = 0.0
            for (edge in hamiltonianCycle.getEdges()) {
                amount += edge.capacity!!
            }
            println(
                getFormattedOutput(
                    resourceName,
                    currentVertex,
                    object {}.javaClass.enclosingMethod.name,
                    end,
                    start,
                    amount
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
        assertThat(hamiltonianCycle.getEdgeCount()).isEqualTo(graph.getVertexCount())
        var amount = 0.0
        for (edge in hamiltonianCycle.getEdges()) {
            amount += edge.capacity!!
        }
        println(getFormattedOutput(resourceName, null, object {}.javaClass.enclosingMethod.name, end, start, amount))
        println()
    }

    private fun getFormattedOutput(
        resourceName: String,
        currentVertex: Vertex?,
        algorithm: String,
        end: Long,
        start: Long,
        amount: Double
    ): String {
        val format = String.format(
            "Graph in: %-10s starting at: %s\t %s: \tcalc time= %sns\tCapacity: %s",
            resourceName,
            currentVertex,
            algorithm,
            (end - start),
            amount
        )
        return format
    }

}