package me.fhaachen.malgo

import java.util.*

class Permutation {
    companion object {
        fun heapsAlgorithmRekursive(A: List<Vertex>, iteration: Int, permutations: MutableList<List<Vertex>>) {
            if (iteration == 1) {
                permutations.add(ArrayList(A))
                return
            }
            heapsAlgorithmRekursive(A, iteration - 1, permutations)
            repeat(iteration - 1) { i ->
                if (i % 2 == 0) {
                    Collections.swap(A, i, iteration - 1)
                } else {
                    Collections.swap(A, 0, iteration - 1)
                }
                heapsAlgorithmRekursive(A, iteration - 1, permutations)
            }
        }
    }
}