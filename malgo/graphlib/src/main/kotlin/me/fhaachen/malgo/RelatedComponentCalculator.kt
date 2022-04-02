package me.fhaachen.malgo

import java.util.*

class RelatedComponentCalculator {

    companion object {
        fun breadthFirstSearch(graph: Graph): Int {
            if (graph.isEmpty()) {
                return 0
            }
            var currentId: Int
            var currentAdjacentVertexId: Int
            var componentCount = 1
            val visitingQueue = LinkedList<Int>()
            val allVertexIds = graph.getIds()
            val visitedIds = BooleanArray(allVertexIds.size)
            var firstUnvisitedId = 0

            visitingQueue.add(firstUnvisitedId)
            while (visitingQueue.isNotEmpty()) {
                currentId = visitingQueue.pollFirst()
                visitedIds[currentId] = true
                val adjacentVertices = graph.getVertex(currentId).getAdjacentVertices().iterator()
                while (adjacentVertices.hasNext()) {
                    currentAdjacentVertexId = adjacentVertices.next().getId()
                    if (!visitedIds[currentAdjacentVertexId]) {
                        visitingQueue.add(currentAdjacentVertexId)
                        visitedIds[currentAdjacentVertexId] = true
                    }
                }
                if (visitingQueue.isEmpty()) {
                    firstUnvisitedId = getFirstUnvisited(visitedIds, firstUnvisitedId)
                    if (firstUnvisitedId < allVertexIds.size) {
                        visitingQueue.add(allVertexIds[firstUnvisitedId])
                        componentCount++
                    }
                }
            }
            return componentCount
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
                if(!visitedIds[currentId]) {
                    visitedIds[currentId] = true
                    val adjacentVertices = graph.getVertex(currentId).getAdjacentVertices().iterator()
                    for (adjacentVertex in adjacentVertices) {
                        stack.push(adjacentVertex.getId())
                    }
                }
                if (stack.isEmpty()) {
                    firstUnvisitedId = getFirstUnvisited(visitedIds, firstUnvisitedId)
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
            while (firstUnvisitedId < visitedIds.size) {
                firstUnvisitedId = getFirstUnvisited(visitedIds, firstUnvisitedId)
                if (firstUnvisitedId < visitedIds.size) {
                    depthFirstSearchRecursive(graph, firstUnvisitedId, visitedIds)
                    componentCount++;
                }
            }
            return componentCount
        }

        private fun depthFirstSearchRecursive(graph: Graph, currentId: Int, visitedIndices: BooleanArray) {
            if(visitedIndices[currentId]){
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

        private fun getFirstUnvisited(visitedIndices: BooleanArray, lastPointer: Int): Int {
            for (curIndex in lastPointer until visitedIndices.size) {
                if (!visitedIndices[curIndex]) {
                    return curIndex
                }
            }
            return visitedIndices.size
        }
    }

}