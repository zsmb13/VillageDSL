package co.zsmb.villagedsl.advanced.nodsl.homemade

import co.zsmb.villagedsl.advanced.models.*

fun main(args: Array<String>) {

    val village = Village(listOf(
            House(listOf(
                    Person(
                            name = "Alice",
                            age = 31
                    ),
                    Person(
                            name = "Bob",
                            age = 45
                    )
            ), listOf(
                    Gold(
                            amount = 500
                    )
            )),
            House(listOf(), listOf(
                    Sword(
                            strength = 24.2
                    ),
                    Sword(
                            strength = 16.7
                    ),
                    Shield(
                            defense = 15.3
                    )
            )),
            House(listOf(), listOf()),
            House(listOf(
                    Person(
                            name = "Charles",
                            age = 52
                    )
            ), listOf(
                    Gold(
                            amount = 2500
                    ),
                    Sword(
                            strength = 0.0
                    ),
                    Shield(
                            defense = 0.0
                    )
            ))
    ))

    print(village)

}
