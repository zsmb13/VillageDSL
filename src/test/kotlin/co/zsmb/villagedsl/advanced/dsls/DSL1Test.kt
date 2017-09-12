package co.zsmb.villagedsl.advanced.dsls

import co.zsmb.villagedsl.advanced.dsl1.main
import co.zsmb.villagedsl.advanced.testMain
import org.junit.Test

class DSL1Test {

    @Test
    fun test() = testMain(::main)

}
