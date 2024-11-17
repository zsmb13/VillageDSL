package co.zsmb.villagedsl.simple.nodsl.javastyle

import co.zsmb.villagedsl.simple.models.House
import co.zsmb.villagedsl.simple.models.Person
import co.zsmb.villagedsl.simple.models.Village

fun main() {

    val houses = mutableListOf<House>()


    val people1 = mutableListOf<Person>()
    people1.add(Person("Emily", 31))
    people1.add(Person("Hannah", 27))
    people1.add(Person("Alex", 21))
    people1.add(Person("Daniel", 17))

    val house1 = House(people1)
    houses.add(house1)


    val people2 = mutableListOf<Person>()
    people2.add(Person("Joe", 48))

    val house2 = House(people2)
    houses.add(house2)


    val people3 = mutableListOf<Person>()

    val house3 = House(people3)
    houses.add(house3)


    val people4 = mutableListOf<Person>()
    people4.add(Person("Sarah", 40))
    people4.add(Person("Tom", 26))
    people4.add(Person("Holly", 52))

    val house4 = House(people4)
    houses.add(house4)


    val village = Village(houses)

    print(village)

}
