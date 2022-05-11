package me.fhaachen.malgo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PermutationTest {

    @Test
    fun permutateSimpleList_4() {
        val permutations = mutableListOf<List<Vertex>>()
        Permutation.heapsAlgorithmRekursive(
            listOf(
                Vertex(0), Vertex(1), Vertex(2), Vertex(3)
            ), 4, permutations
        )
        assertThat(permutations).hasSize(24)
    }

    @Test
    fun permutateSimpleList_5() {
        val permutations = mutableListOf<List<Vertex>>()
        Permutation.heapsAlgorithmRekursive(
            listOf(
                Vertex(0), Vertex(1), Vertex(2), Vertex(3), Vertex(4)
            ), 5, permutations
        )
        assertThat(permutations).hasSize(120)
    }

    @Test
    fun permutateSimpleList_10() {
        val permutations = mutableListOf<List<Vertex>>()
        Permutation.heapsAlgorithmRekursive(
            listOf(
                Vertex(0), Vertex(1), Vertex(2), Vertex(3), Vertex(4), Vertex(5),
                Vertex(6), Vertex(7), Vertex(8), Vertex(9)
            ), 10, permutations
        )
        assertThat(permutations).hasSize(3628800)
    }
}