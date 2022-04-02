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
        Assertions.assertThat(graph.getVertex(vertexId).getAdjacentVertices()
            .stream()
            .map { v -> v.getId() })
            .containsExactly(1, 2, 3, 4)

        // vertex index 5
        index = 5
        vertexId = graph.getIds()[index]
        Assertions.assertThat(vertexId).isEqualTo(index)
        Assertions.assertThat(graph.getVertex(vertexId).getAdjacentVertices()
            .stream()
            .map { v -> v.getId() })
            .containsExactly(6, 7, 8, 9)

        // vertex index 3
        index = 3
        vertexId = graph.getIds()[index]
        Assertions.assertThat(vertexId).isEqualTo(index)
        Assertions.assertThat(graph.getVertex(vertexId).getAdjacentVertices()
            .stream()
            .map { v -> v.getId() })
            .containsExactly(0, 3)

        // vertex index 7
        index = 7
        vertexId = graph.getIds()[index]
        Assertions.assertThat(vertexId).isEqualTo(index)
        Assertions.assertThat(graph.getVertex(vertexId).getAdjacentVertices()
            .stream()
            .map { v -> v.getId() })
            .containsExactly(5, 6)
    }

    companion object{
        fun toGraph(resourceName: String): Graph {
            val file = File(javaClass.classLoader.getResource(resourceName)!!.file)
            println("Read: $resourceName")

            val start = System.currentTimeMillis()
            val lines = file.readLines(Charsets.UTF_8).toMutableList()
            val numberOfVertices = lines.removeAt(0)
            val graph = UndirectedGraph(numberOfVertices.toInt())
            for (line in lines) {
                val split = line.split("\t")
                graph.connectVertices(Vertex(split[0]), Vertex(split[1]))
            }
            val end = System.currentTimeMillis()
            Assertions.assertThat(graph.getIds().size).isEqualTo(numberOfVertices.toInt())
            println("Read time = " + (end - start))
            return graph
        }

        fun undirectedGraph(): UndirectedGraph {
            val graph = UndirectedGraph(10)
            graph.connectVertices(Vertex(0), Vertex(1))
            graph.connectVertices(Vertex(0), Vertex(2))
            graph.connectVertices(Vertex(0), Vertex(3))
            graph.connectVertices(Vertex(3), Vertex(3))
            graph.connectVertices(Vertex(0), Vertex(4))
            graph.connectVertices(Vertex(5), Vertex(6))
            graph.connectVertices(Vertex(6), Vertex(7))
            graph.connectVertices(Vertex(5), Vertex(7))
            graph.connectVertices(Vertex(5), Vertex(8))
            graph.connectVertices(Vertex(5), Vertex(9))
            return graph
        }
    }
}