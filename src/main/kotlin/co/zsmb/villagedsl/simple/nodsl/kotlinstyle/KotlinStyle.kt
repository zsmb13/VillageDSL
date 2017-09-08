package co.zsmb.villagedsl.simple.nodsl.kotlinstyle

import co.zsmb.villagedsl.simple.models.House
import co.zsmb.villagedsl.simple.models.Person
import co.zsmb.villagedsl.simple.models.Village

fun main(args: Array<String>) {

    val house1 = House(listOf(
            Person("Emily", 31),
            Person("Hannah", 27),
            Person("Alex", 21),
            Person("Daniel", 17)))

    val house2 = House(listOf(Person("Joe", 48)))

    val house3 = House(listOf())

    val house4 = House(listOf(
            Person("Sarah", 40),
            Person("Tom", 26),
            Person("Holly", 52)))

    val village = Village(listOf(house1, house2, house3, house4))

    print(village)

}
