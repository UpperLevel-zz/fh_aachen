package me.fhaachen.malgo

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class GraphTest {

    @Test
    fun breitensuche_two_components_ten_vertices() {
        val graph = undirectedGraph()

        // vertex index 0
        var index = 0
        var vertexId = graph.getIds()[index]
        Assertions.assertThat(vertexId).isEqualTo(index)
        Assertions.assertThat(graph.getVertex(vertexId).getAdjacentVertices())
            .containsExactly(1, 2, 3, 4)

        // vertex index 5
        index = 5
        vertexId = graph.getIds()[index]
        Assertions.assertThat(vertexId).isEqualTo(index)
        Assertions.assertThat(graph.getVertex(vertexId).getAdjacentVertices())
            .containsExactly(6, 7, 8, 9)

        // vertex index 3
        index = 3
        vertexId = graph.getIds()[index]
        Assertions.assertThat(vertexId).isEqualTo(index)
        Assertions.assertThat(graph.getVertex(vertexId).getAdjacentVertices())
            .containsExactly(0, 3)

        // vertex index 7
        index = 7
        vertexId = graph.getIds()[index]
        Assertions.assertThat(vertexId).isEqualTo(index)
        Assertions.assertThat(graph.getVertex(vertexId).getAdjacentVertices())
            .containsExactly(5, 6)
    }

    companion object {
        fun toGraph(resourceName: String): Graph {
            val file = File(this::class.java.classLoader.getResource(resourceName)!!.file)
            val lines = file.readLines(Charsets.UTF_8).toMutableList()
            val numberOfVertices = lines.removeAt(0).trim().toInt()
            val graph = UndirectedGraph(numberOfVertices)
            for (line in lines) {
                val split = line.split("\t")
                if (split.size == 2)
                    graph.connectVertices(Edge(Vertex(split[0]), Vertex(split[1])))
                else if (split.size == 3)
                    graph.connectVertices(Edge(Vertex(split[0]), Vertex(split[1]), split[2]))
            }
            Assertions.assertThat(graph.getIds().size).isEqualTo(numberOfVertices)
            return graph
        }

        fun toDiGraph(resourceName: String): Graph {
            val file = File(this::class.java.classLoader.getResource(resourceName)!!.file)
            val lines = file.readLines(Charsets.UTF_8).toMutableList()
            val numberOfVertices = lines.removeAt(0).trim()
            val graph = DiGraph()
            for (line in lines) {
                val split = line.split("\t")
                if (split.size == 2)
                    graph.connectVertices(Edge(Vertex(split[0].trim()), Vertex(split[1].trim())))
                else if (split.size == 3)
                    graph.connectVertices(Edge(Vertex(split[0].trim()), Vertex(split[1].trim()), split[2].trim()))
            }
            Assertions.assertThat(graph.getIds().size).isEqualTo(numberOfVertices.toInt())
            return graph
        }

        fun undirectedGraph(): UndirectedGraph {
            val graph = UndirectedGraph(10)
            graph.connectVertices(Edge(Vertex(0), Vertex(1)))
            graph.connectVertices(Edge(Vertex(0), Vertex(2)))
            graph.connectVertices(Edge(Vertex(0), Vertex(3)))
            graph.connectVertices(Edge(Vertex(3), Vertex(3)))
            graph.connectVertices(Edge(Vertex(0), Vertex(4)))
            graph.connectVertices(Edge(Vertex(5), Vertex(6)))
            graph.connectVertices(Edge(Vertex(6), Vertex(7)))
            graph.connectVertices(Edge(Vertex(5), Vertex(7)))
            graph.connectVertices(Edge(Vertex(5), Vertex(8)))
            graph.connectVertices(Edge(Vertex(5), Vertex(9)))
            return graph
        }

        fun getFormattedOutput(
            resourceName: String,
            currentVertex: Int?,
            algorithm: String,
            end: Long,
            start: Long,
            amount: Double
        ): String {
            return String.format(
                "Graph in: %-10s starting at: %s\t %s: \tcalc time= %sms\tCapacity: %s",
                resourceName,
                currentVertex,
                algorithm,
                (end - start) / 1000000,
                amount
            )
        }
    }
}