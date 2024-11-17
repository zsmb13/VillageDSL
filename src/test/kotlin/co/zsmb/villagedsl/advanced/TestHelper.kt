package co.zsmb.villagedsl.advanced

import org.junit.Assert
import java.io.ByteArrayOutputStream
import java.io.PrintStream

val expected = "Village(houses=[House(people=[Person(name=Alice, age=31), Person(name=Bob, age=45)], items=[Gold(amount=500)]), House(people=[], items=[Sword(strength=24.2), Sword(strength=16.7), Shield(defense=15.3)]), House(people=[], items=[]), House(people=[Person(name=Charles, age=52)], items=[Gold(amount=2500), Sword(strength=0.0), Shield(defense=0.0)])])"

inline fun captureStdOut(block: () -> Unit): String {
    val outStream = ByteArrayOutputStream()
    System.setOut(PrintStream(outStream))
    block()
    System.setOut(null)
    return outStream.toString()
}

inline fun testMain(main: () -> Unit) {
    val out = captureStdOut { main() }
    Assert.assertEquals(expected, out)
}
