package me.fhaachen.malgo.input

import me.fhaachen.malgo.DiGraph
import me.fhaachen.malgo.Edge
import me.fhaachen.malgo.Vertex
import java.io.BufferedReader

class MinCostFlowFormat {

    companion object {
        private fun toGraph(bufferedReader: BufferedReader): DiGraph {
            val graph = DiGraph()
            val numberOfVertices = bufferedReader.readLine().toInt()
            bufferedReader.use { reader ->
                val balances = DoubleArray(numberOfVertices)
                for (i in 0 until numberOfVertices) {
                    balances[i] = reader.readLine().trim().toDouble()
                }

                reader.forEachLine { line ->
                    val split = line.trim().split("\t")
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
            }
            return graph
        }

        fun toGraph(resourceName: String): DiGraph {
            val bufferedReader = BufferedReader(
                this::class.java.classLoader.getResourceAsStream(resourceName)!!.bufferedReader(Charsets.UTF_8)
            )
            val graph = toGraph(bufferedReader)
            graph.postInit()
            return graph
        }
    }

}