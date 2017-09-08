package simple.nodsl.homemade

import simple.models.*

fun main(args: Array<String>) {

    val village = Village(listOf(
            House(listOf(
                    Person(
                            name = "Emily",
                            age = 31
                    ),
                    Person(
                            name = "Hannah",
                            age = 27
                    ),
                    Person(
                            name = "Alex",
                            age = 21
                    ),
                    Person(
                            name = "Daniel",
                            age = 17
                    )
            )),
            House(listOf(
                    Person(
                            name = "Joe",
                            age = 48
                    )
            )),
            House(listOf(
            )),
            House(listOf(
                    Person(
                            name = "Sarah",
                            age = 40
                    ),
                    Person(
                            name = "Tom",
                            age = 26
                    ),
                    Person(
                            name = "Holly",
                            age = 52
                    )
            ))
    ))

    print(village)

}
