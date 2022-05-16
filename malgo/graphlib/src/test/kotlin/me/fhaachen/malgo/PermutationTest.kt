package me.fhaachen.malgo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PermutationTest {

    @Test
    fun permutateSimpleList_4() {
        val permutations = mutableListOf<List<Int>>()
        Permutation.heapsAlgorithmRekursive(
            listOf(
                0, 1, 2, 3
            ), 4, permutations
        )
        assertThat(permutations).hasSize(24)
    }

    @Test
    fun permutateSimpleList_5() {
        val permutations = mutableListOf<List<Int>>()
        Permutation.heapsAlgorithmRekursive(
            listOf(
                0, 1, 2, 3, 4
            ), 5, permutations
        )
        assertThat(permutations).hasSize(120)
    }

    @Test
    fun permutateSimpleList_10() {
        val permutations = mutableListOf<List<Int>>()
        Permutation.heapsAlgorithmRekursive(
            listOf(
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9
            ), 10, permutations
        )
        assertThat(permutations).hasSize(3628800)
    }
}