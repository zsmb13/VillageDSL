package co.zsmb.villagedsl.advanced.dsl1

import co.zsmb.villagedsl.advanced.models.House
import co.zsmb.villagedsl.advanced.models.Item
import co.zsmb.villagedsl.advanced.models.Person
import co.zsmb.villagedsl.advanced.models.Village

@DslMarker
annotation class AdvancedDSL1

@AdvancedDSL1
class PersonBuilder(initialName: String, initialAge: Int) {
    var name: String = initialName
    var age: Int = initialAge

    fun build(): Person {
        return Person(name, age)
    }

}

@AdvancedDSL1
class HouseBuilder {

    internal val people = mutableListOf<Person>()
    internal val items = mutableListOf<Item>()

    fun build(): House {
        return House(people, items)
    }

    fun person(name: String = "", age: Int = 0, setup: PersonBuilder.() -> Unit = {}) {
        val personBuilder = PersonBuilder(name, age)
        personBuilder.setup()
        people += personBuilder.build()
    }

}

@AdvancedDSL1
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

    /**
     * This method shadows the [co.zsmb.villagedsl.advanced.dsl1.village] method when inside the scope
     * of a [VillageBuilder], so that villages can't be nested.
     */
    @Suppress("UNUSED_PARAMETER")
    @Deprecated(level = DeprecationLevel.ERROR,
            message = "Villages can't be nested.")
    fun village(param: () -> Unit = {}) {
    }

}

fun village(setup: VillageBuilder.() -> Unit): Village {
    val villageBuilder = VillageBuilder()
    villageBuilder.setup()
    return villageBuilder.build()
}
