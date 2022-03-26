package me.fhaachen.malgo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.io.File

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GraphTest {

    @Test
    fun name() {
        val resourceName = "Graph1.txt"

        val classLoader = javaClass.classLoader
        val file = File(classLoader.getResource(resourceName).file)
        val absolutePath = file.absolutePath

        println(absolutePath)

        assertThat(absolutePath).endsWith("Graph1.txt")
    }

}