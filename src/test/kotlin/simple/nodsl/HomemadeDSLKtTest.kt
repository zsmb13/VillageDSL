package simple.nodsl

import org.junit.Test
import simple.nodsl.homemade.main
import simple.testMain

class HomemadeDSLKtTest {

    @Test
    fun test() = testMain(::main)

}
