package me.fhaachen.malgo

class TravelingSalesman {

    companion object {
        fun nearestNeighbor(graph: Graph): Graph {
            val hc = HamiltonianCycle()
            val vertices = graph.getVertices()
            val visited = BooleanArray(vertices.size)
            var currentVertex = vertices.poll()
            while (!visited[currentVertex.getId()]) {
                visited[currentVertex.getId()] = true
                for (outgoingEdge in currentVertex.outgoingEdges) {
                    if (!visited[outgoingEdge.target.getId()]) {
                        hc.connectVertices(outgoingEdge)
                        currentVertex = outgoingEdge.target
                        break
                    }
                }
            }
            return hc
        }

        fun doppelterBaum(graph: Graph): Graph {
            val mst = MinimumSpanningTree.prim(graph)
            val depthFirstSearch = RelatedComponentCalculator.depthFirstSearch(mst)
            val result = depthFirstSearch.first()
            return translateToGraph(result)
        }

        fun bruteForce(graph: Graph): Graph {
            var result = HamiltonianCycle()
            val vertices = graph.getVertices()
            val permutations = getPermutationsWithDistinctValues(vertices)
            var amount: Double
            var lowestAmount = Double.MAX_VALUE
            for (vertexList in permutations) {
                amount = 0.0
                val hamiltonianCycle = translateToGraph(vertexList)
                for (edge in hamiltonianCycle.getEdges()) {
                    amount += edge.capacity!!
                }
                if (lowestAmount > amount) {
                    lowestAmount = amount
                    result = hamiltonianCycle
                }
            }
            return result
        }

        private fun translateToGraph(searchTree: List<Vertex>): HamiltonianCycle {
            val result = HamiltonianCycle()
            val visited = BooleanArray(searchTree.size)
            var previousVertex = searchTree.first()
            for (currentVertex in searchTree) {
                visited[previousVertex.getId()] = true
                if (!visited[currentVertex.getId()]) {
                    val edge = previousVertex.getEdge(currentVertex.getId())
                    edge.let { it.let { it1 -> result.connectVertices(it1) } }
                }
                previousVertex = currentVertex
            }
            return result
        }

        fun <T> getPermutationsWithDistinctValues(original: List<T>): Set<List<T>> {
            if (original.isEmpty())
                return emptySet()
            val permutationInstructions = original.toSet()
                .map { it to original.count { x -> x == it } }
                .fold(listOf(setOf<Pair<T, Int>>())) { acc, (value, valueCount) ->
                    mutableListOf<Set<Pair<T, Int>>>().apply {
                        for (set in acc) for (retainIndex in 0 until valueCount) add(set + (value to retainIndex))
                    }
                }
            return mutableSetOf<List<T>>().also { outSet ->
                for (instructionSet in permutationInstructions) {
                    outSet += original.toMutableList().apply {
                        for ((value, retainIndex) in instructionSet) {
                            repeat(retainIndex) { removeAt(indexOfFirst { it == value }) }
                            repeat(count { it == value } - 1) { removeAt(indexOfLast { it == value }) }
                        }
                    }
                }
            }
        }
    }
}