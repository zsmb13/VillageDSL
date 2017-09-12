package co.zsmb.villagedsl.advanced.nodsl

import co.zsmb.villagedsl.advanced.nodsl.homemade.main
import co.zsmb.villagedsl.advanced.testMain
import org.junit.Test

class HomemadeTest {

    @Test
    fun test() = testMain(::main)

}