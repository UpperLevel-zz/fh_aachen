package me.fhaachen.malgo

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MinimumSpanningTreeTest {

    @Test
    fun G_1_2_prim() {
        val resourceName = "G_1_2.txt"
        val graph = GraphTest.toGraph(resourceName)
        val prim = MinimumSpanningTree.prim(graph)
        println(prim)
        Assertions.assertThat(prim).isNotEmpty()
    }
}