package co.zsmb.villagedsl.simple.dsl3

import co.zsmb.villagedsl.simple.models.*

object village {
    infix fun containing(houses: List<House>) = Village(houses)
}

object house

class PeopleListBuilder {

    val people = mutableListOf<Person>()

    fun build(): List<Person> = people

    infix fun String.age(age: Int) {
        people.add(Person(this, age))
    }

}

fun people(actions: PeopleListBuilder.() -> Unit): List<Person> {
    val builder = PeopleListBuilder()
    builder.actions()
    return builder.build()
}

fun person(actions: PeopleListBuilder.() -> Unit) = people(actions)

object people

class HouseListBuilder {

    private val houses = mutableListOf<House>()

    fun build(): List<House> = houses

    infix fun house.with(people: List<Person>) {
        houses += House(people)
    }

    infix fun house.of(people: List<Person>) = with(people)

    infix fun house.without(p: people) {
        houses += House(listOf())
    }

}

fun houses(actions: HouseListBuilder.() -> Unit): List<House> {
    val builder = HouseListBuilder()
    builder.actions()
    return builder.build()
}
