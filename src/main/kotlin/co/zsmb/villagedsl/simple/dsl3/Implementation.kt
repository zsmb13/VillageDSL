package co.zsmb.villagedsl.simple.dsl3

import co.zsmb.villagedsl.simple.models.House
import co.zsmb.villagedsl.simple.models.Person
import co.zsmb.villagedsl.simple.models.Village

@DslMarker
annotation class SimpleDsl3

@SimpleDsl3
object village {
    @SimpleDsl3
    infix fun containing(houses: List<House>) = Village(houses)
}

@SimpleDsl3
object house

@SimpleDsl3
class PeopleListBuilder {

    val people = mutableListOf<Person>()

    fun build(): List<Person> = people

    @SimpleDsl3
    infix fun String.age(age: Int) {
        people.add(Person(this, age))
    }

}

@SimpleDsl3
fun people(actions: PeopleListBuilder.() -> Unit): List<Person> {
    val builder = PeopleListBuilder()
    builder.actions()
    return builder.build()
}

@SimpleDsl3
fun person(actions: PeopleListBuilder.() -> Unit) = people(actions)

@SimpleDsl3
object people

class HouseListBuilder {

    private val houses = mutableListOf<House>()

    fun build(): List<House> = houses

    @SimpleDsl3
    infix fun house.with(people: List<Person>) {
        houses += House(people)
    }

    @SimpleDsl3
    infix fun house.of(people: List<Person>) = with(people)

    @Suppress("UNUSED_PARAMETER")
    @SimpleDsl3
    infix fun house.without(p: people) {
        houses += House(emptyList())
    }

}

@SimpleDsl3
fun houses(actions: HouseListBuilder.() -> Unit): List<House> {
    val builder = HouseListBuilder()
    builder.actions()
    return builder.build()
}
