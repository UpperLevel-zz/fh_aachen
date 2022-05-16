package me.fhaachen.malgo

import java.util.*

class Permutation {
    companion object {
        fun heapsAlgorithmRekursive(quantity: List<Int>, iteration: Int, permutations: MutableList<List<Int>>) {
            if (iteration == 1) {
                permutations.add(ArrayList(quantity))
                return
            }
            heapsAlgorithmRekursive(quantity, iteration - 1, permutations)
            repeat(iteration - 1) { i ->
                if (i % 2 == 0) {
                    Collections.swap(quantity, i, iteration - 1)
                } else {
                    Collections.swap(quantity, 0, iteration - 1)
                }
                heapsAlgorithmRekursive(quantity, iteration - 1, permutations)
            }
        }
    }
}