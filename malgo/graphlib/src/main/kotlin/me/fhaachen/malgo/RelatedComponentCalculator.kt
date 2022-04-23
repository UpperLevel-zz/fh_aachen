package me.fhaachen.malgo

import java.util.*

class RelatedComponentCalculator {

    companion object {
        fun breadthFirstSearch(graph: Graph): Int {
            if (graph.isEmpty()) {
                return 0
            }
            var componentCount = 0
            val allVertexIds = graph.getIds()
            val visitedIds = BooleanArray(allVertexIds.size)
            var nextUnvisitedId = 0
            while (nextUnvisitedId < allVertexIds.size) {
                visitedIds[nextUnvisitedId] = true
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
            var componentCount = 0
            val allVertexIds = graph.getIds()
            val visitedIds = BooleanArray(allVertexIds.size)
            var nextUnvisitedId = 0
            while (nextUnvisitedId < allVertexIds.size) {
                depthFirstSearch(graph, nextUnvisitedId, visitedIds)
                nextUnvisitedId = getNextUnvisitedId(visitedIds, nextUnvisitedId)
                componentCount++
            }
            return componentCount
        }

        private fun depthFirstSearch(graph: Graph, startId: Int, visitedIds: BooleanArray) {
            val stack = Stack<Int>()
            var currentId: Int
            stack.push(startId)
            while (stack.isNotEmpty()) {
                currentId = stack.pop()
                if (!visitedIds[currentId]) {
                    visitedIds[currentId] = true
                    val adjacentVertices = graph.getVertex(currentId).getAdjacentVertices().iterator()
                    for (adjacentVertex in adjacentVertices) {
                        stack.push(adjacentVertex.getId())
                    }
                }
            }
        }

        fun depthFirstSearchRecursive(graph: Graph): Int {
            if (graph.isEmpty()) {
                return 0
            }
            val visitedIds = BooleanArray(graph.getIds().size)
            var componentCount = 0
            var firstUnvisitedId = 0
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