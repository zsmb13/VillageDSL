package co.zsmb.villagedsl.simple.nodsl

import org.junit.Test
import co.zsmb.villagedsl.simple.nodsl.homemade.main
import co.zsmb.villagedsl.simple.testMain

class HomemadeDSLKtTest {

    @Test
    fun test() = testMain(::main)

}
