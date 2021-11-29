package co.zsmb.villagedsl.advanced.dsl2

import co.zsmb.villagedsl.advanced.models.*

@DslMarker
annotation class AdvancedDSL2

class HouseBuilder {

    internal val people = mutableListOf<Person>()
    internal val items = mutableListOf<Item>()

    fun build(): House {
        return House(people, items)
    }

    @AdvancedDSL2
    infix fun String.age(age: Int) {
        people.add(Person(this, age))
    }

    @AdvancedDSL2
    val Int.gold: Unit
        get() {
            items += Gold(this)
            return Unit
        }

}

class VillageBuilder {

    private val houses = mutableListOf<House>()

    @AdvancedDSL2
    operator fun House.unaryPlus() {
        houses += this
    }

    @AdvancedDSL2
    fun house(setup: HouseBuilder.() -> Unit = {}) {
        val houseBuilder = HouseBuilder()
        houseBuilder.setup()
        houses += houseBuilder.build()
    }

    fun build(): Village {
        return Village(houses)
    }

}

@AdvancedDSL2
fun village(setup: VillageBuilder.() -> Unit): Village {
    val villageBuilder = VillageBuilder()
    villageBuilder.setup()
    return villageBuilder.build()
}
