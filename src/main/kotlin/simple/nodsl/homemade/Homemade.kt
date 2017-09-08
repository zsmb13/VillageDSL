package simple.nodsl.homemade

import simple.models.*

fun main(args: Array<String>) {

    val village = Village(listOf(
            House(listOf(
                    Person("Emily", 31),
                    Person("Hannah", 27),
                    Person("Alex", 21),
                    Person("Daniel", 17)
            )),
            House(listOf(
                    Person("Joe", 48)
            )),
            House(listOf(
            )),
            House(listOf(
                    Person("Sarah", 40),
                    Person("Tom", 26),
                    Person("Holly", 52)
            ))
    ))

    print(village)

}
