package co.zsmb.villagedsl.simple.dsl2

import co.zsmb.villagedsl.simple.models.House
import co.zsmb.villagedsl.simple.models.Person
import co.zsmb.villagedsl.simple.models.Village

@DslMarker
annotation class SimpleDSL2

class HouseBuilder {

    private val people = mutableListOf<Person>()

    fun build(): House {
        return House(people)
    }

    @SimpleDSL2
    operator fun Person.unaryPlus() {
        people += this
    }

    @SimpleDSL2
    operator fun Person.unaryMinus() {
        people += this
    }

}

class VillageBuilder {

    private val houses = mutableListOf<House>()

    @SimpleDSL2
    operator fun House.unaryPlus() {
        houses += this
    }

    @SimpleDSL2
    fun house(setup: HouseBuilder.() -> Unit = {}) {
        val houseBuilder = HouseBuilder()
        houseBuilder.setup()
        houses += houseBuilder.build()
    }

    fun build(): Village {
        return Village(houses)
    }

    /**
     * This method shadows the [co.zsmb.villagedsl.simple.dsl2.village] method when inside the scope
     * of a [VillageBuilder], so that villages can't be nested.
     */
    @Suppress("UNUSED_PARAMETER")
    @Deprecated(level = DeprecationLevel.ERROR,
            message = "Villages can't be nested.")
    fun village(param: () -> Unit = {}) {
    }

}

@SimpleDSL2
fun village(setup: VillageBuilder.() -> Unit): Village {
    val villageBuilder = VillageBuilder()
    villageBuilder.setup()
    return villageBuilder.build()
}
