package me.fhaachen.malgo.input

import me.fhaachen.malgo.DiGraph
import me.fhaachen.malgo.Edge
import me.fhaachen.malgo.Graph
import me.fhaachen.malgo.Vertex
import java.io.File

class MinCostFlowFormat {

    companion object {
        private fun toGraph(lines: MutableList<String>, numberOfVertices: Int): Graph {
            val graph = DiGraph(numberOfVertices)
            val balances = DoubleArray(numberOfVertices)
            for (i in 0 until numberOfVertices) {
                balances[i] = lines[i].trim().toDouble()
            }

            for (i in numberOfVertices until 2 * numberOfVertices - 2) {
                val split = lines[i].trim().split("\t")
                val idFirst = split[0].trim().toInt()
                val idSecond = split[1].trim().toInt()
                graph.connectVertices(
                    Edge(
                        Vertex(idFirst, balances[idFirst]),
                        Vertex(idSecond, balances[idSecond]),
                        split[2].trim().toDouble(),
                        split[3].trim().toDouble()
                    )
                )
            }
            assert(graph.getIds().size == numberOfVertices)
            return graph
        }

        fun toGraph(resourceName: String): Graph {
            val file = File(this::class.java.classLoader.getResource(resourceName)!!.file)
            val lines = file.readLines(Charsets.UTF_8).toMutableList()
            val numberOfVertices = lines.removeAt(0).toInt()
            return toGraph(lines, numberOfVertices)
        }
    }

}