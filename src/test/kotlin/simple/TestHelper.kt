package simple

import org.junit.Assert.assertEquals
import java.io.ByteArrayOutputStream
import java.io.PrintStream

val expected = "Village(houses=[House(people=[Person(name=Emily, age=31), Person(name=Hannah, age=27), Person(name=Alex, age=21), Person(name=Daniel, age=17)]), House(people=[Person(name=Joe, age=48)]), House(people=[]), House(people=[Person(name=Sarah, age=40), Person(name=Tom, age=26), Person(name=Holly, age=52)])])"

inline fun captureStdOut(block: () -> Unit): String {
    val outStream = ByteArrayOutputStream()
    System.setOut(PrintStream(outStream))
    block()
    System.setOut(null)
    return outStream.toString()
}

inline fun testMain(main: (Array<String>) -> Unit) {
    val out = captureStdOut {
        main(arrayOf())
    }
    assertEquals(expected, out)
}
