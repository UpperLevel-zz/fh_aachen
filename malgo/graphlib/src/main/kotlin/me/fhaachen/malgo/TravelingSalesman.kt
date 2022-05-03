package me.fhaachen.malgo

class TravelingSalesman {

    companion object {
        fun nearestNeighbor(graph: Graph, startVertex: Vertex): Graph {
            val hc = HamiltonianCycle()
            val vertices = graph.getVertices()
            val visited = BooleanArray(vertices.size)
            var currentVertex = startVertex
            var amount: Double
            var lowestEdge: Edge?
            while (!visited[currentVertex.getId()]) {
                lowestEdge = null
                visited[currentVertex.getId()] = true
                amount = Double.MAX_VALUE
                for (outgoingEdge in currentVertex.outgoingEdges) {
                    if (!visited[outgoingEdge.target.getId()] && outgoingEdge.capacity!! < amount) {
                        lowestEdge = outgoingEdge
                        amount = lowestEdge.capacity!!
                    }
                }
                if (lowestEdge != null) {
                    hc.connectVertices(lowestEdge)
                    currentVertex = lowestEdge.target
                }
            }
            hc.connectVertices(currentVertex.getEdge(startVertex.getId()))
            return hc
        }

        fun doppelterBaum(graph: Graph, startVertex: Vertex): Graph {
            val mst = MinimumSpanningTree.prim(graph, startVertex)
            val depthFirstSearch = RelatedComponentCalculator.depthFirstSearch(mst)
            val result = depthFirstSearch.first()
            return translateToDistinctGraph(result)
        }

        fun bruteForce(graph: Graph): Graph {
            var result = HamiltonianCycle()
            val vertices = graph.getVertices()
            val permutations = getPermutationsWithDistinctValues(vertices)
            var amount: Double
            var lowestAmount = Double.MAX_VALUE
            for (vertexList in permutations) {
                amount = 0.0
                val hamiltonianCycle = translateToDistinctGraph(vertexList)
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

        private fun translateToDistinctGraph(searchTree: List<Vertex>): HamiltonianCycle {
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
            result.connectVertices(previousVertex.getEdge(searchTree.first().getId()))
            return result
        }

        /**
         * @see <a href=https://stackoverflow.com/a/59737650 >Permutations</a>
         */
        private fun <T> getPermutationsWithDistinctValues(original: List<T>): Set<List<T>> {
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