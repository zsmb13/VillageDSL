package simple.dsl2

import simple.models.*


class HouseBuilder {

    private val people = mutableListOf<Person>()

    fun build(): House {
        return House(people)
    }

    operator fun Person.unaryPlus() {
        people += this
    }

    operator fun Person.unaryMinus() {
        people += this
    }

}

class VillageBuilder {

    private val houses = mutableListOf<House>()

    operator fun House.unaryPlus() {
        houses += this
    }

    fun house(setup: HouseBuilder.() -> Unit = {}) {
        val houseBuilder = HouseBuilder()
        houseBuilder.setup()
        houses += houseBuilder.build()
    }

    fun build(): Village {
        return Village(houses)
    }

}

fun village(setup: VillageBuilder.() -> Unit): Village {
    val villageBuilder = VillageBuilder()
    villageBuilder.setup()
    return villageBuilder.build()
}
