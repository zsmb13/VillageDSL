package co.zsmb.villagedsl.simple.nodsl

import co.zsmb.villagedsl.simple.nodsl.homemade.main
import co.zsmb.villagedsl.simple.testMain
import org.junit.Test

class HomemadeTest {

    @Test
    fun test() = testMain(::main)

}
