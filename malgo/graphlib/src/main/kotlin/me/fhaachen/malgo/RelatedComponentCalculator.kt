package me.fhaachen.malgo

import java.util.*

class RelatedComponentCalculator {

    companion object {
        fun breadthFirstSearch(graph: Graph): Int {
            if (graph.isEmpty()) {
                return 0
            }
            var componentCount = 0
            val visitingQueue = LinkedList<Int>()
            val allVertexIds = graph.getIds()
            val visitedIds = BooleanArray(allVertexIds.size)
            var nextUnvisitedId = 0
            while (nextUnvisitedId < allVertexIds.size) {
                visitedIds[nextUnvisitedId] = true
                visitingQueue.add(allVertexIds[nextUnvisitedId])
                breadthFirstSearch(graph, nextUnvisitedId, visitedIds)
                nextUnvisitedId = getNextUnvisitedId(visitedIds, nextUnvisitedId)
                componentCount++
            }
            return componentCount
        }

        private fun breadthFirstSearch(graph: Graph, startId: Int, visitedIds: BooleanArray) {
            if (graph.isEmpty()) {
                return
            }
            var currentAdjacentVertexId: Int
            val visitingQueue = LinkedList<Int>()
            var currentId = startId
            visitedIds[currentId] = true
            visitingQueue.add(currentId)
            while (visitingQueue.isNotEmpty()) {
                currentId = visitingQueue.pollFirst()
                val adjacentVertices = graph.getVertex(currentId).getAdjacentVertices().iterator()
                while (adjacentVertices.hasNext()) {
                    currentAdjacentVertexId = adjacentVertices.next().getId()
                    if (!visitedIds[currentAdjacentVertexId]) {
                        visitingQueue.add(currentAdjacentVertexId)
                        visitedIds[currentAdjacentVertexId] = true
                    }
                }
            }
        }

        fun depthFirstSearch(graph: Graph): Int {
            if (graph.isEmpty()) {
                return 0
            }
            var currentId: Int
            var componentCount = 1
            val stack = Stack<Int>()
            val allVertexIds = graph.getIds()
            val visitedIds = BooleanArray(allVertexIds.size)
            var firstUnvisitedId = 0

            stack.push(firstUnvisitedId)
            while (stack.isNotEmpty()) {
                currentId = stack.pop()
                if (!visitedIds[currentId]) {
                    visitedIds[currentId] = true
                    val adjacentVertices = graph.getVertex(currentId).getAdjacentVertices().iterator()
                    for (adjacentVertex in adjacentVertices) {
                        stack.push(adjacentVertex.getId())
                        visitedIds[adjacentVertex.getId()] = true
                    }
                }
                // todo auserhalb der schleife
                if (stack.isEmpty()) {
                    firstUnvisitedId = getNextUnvisitedId(visitedIds, firstUnvisitedId)
                    if (firstUnvisitedId < allVertexIds.size) {
                        stack.push(allVertexIds[firstUnvisitedId])
                        componentCount++
                    }
                }
            }
            return componentCount
        }

        fun depthFirstSearchRecursive(graph: Graph): Int {
            if (graph.isEmpty()) {
                return 0
            }
            val visitedIds = BooleanArray(graph.getIds().size)
            var componentCount = 0
            var firstUnvisitedId = 0
            //todo for schleife
            while (firstUnvisitedId < visitedIds.size) {
                firstUnvisitedId = getNextUnvisitedId(visitedIds, firstUnvisitedId)
                if (firstUnvisitedId < visitedIds.size) {
                    depthFirstSearchRecursive(graph, firstUnvisitedId, visitedIds)
                    componentCount++
                }
            }
            return componentCount
        }

        private fun depthFirstSearchRecursive(graph: Graph, currentId: Int, visitedIndices: BooleanArray) {
            if (visitedIndices[currentId]) {
                println("Recursion Failure @$currentId")
                return
            }
            val adjacentVertices = graph.getVertex(currentId).getAdjacentVertices()
            visitedIndices[currentId] = true
            for (vertex in adjacentVertices) {
                if (!visitedIndices[vertex.getId()]) {
                    depthFirstSearchRecursive(graph, vertex.getId(), visitedIndices)
                }
            }
        }

        private fun getNextUnvisitedId(visitedIndices: BooleanArray, lastPointer: Int): Int {
            for (curIndex in lastPointer until visitedIndices.size) {
                if (!visitedIndices[curIndex]) {
                    return curIndex
                }
            }
            return visitedIndices.size
        }
    }

}