package co.zsmb.villagedsl.simple.nodsl.homemade

import co.zsmb.villagedsl.simple.models.House
import co.zsmb.villagedsl.simple.models.Person
import co.zsmb.villagedsl.simple.models.Village

fun main() {

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
