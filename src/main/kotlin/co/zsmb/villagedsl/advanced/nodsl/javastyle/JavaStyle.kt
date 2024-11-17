package co.zsmb.villagedsl.advanced.nodsl.javastyle

import co.zsmb.villagedsl.advanced.models.Gold
import co.zsmb.villagedsl.advanced.models.House
import co.zsmb.villagedsl.advanced.models.Item
import co.zsmb.villagedsl.advanced.models.Person
import co.zsmb.villagedsl.advanced.models.Shield
import co.zsmb.villagedsl.advanced.models.Sword
import co.zsmb.villagedsl.advanced.models.Village

fun main() {

    val houses = mutableListOf<House>()


    val people1 = mutableListOf<Person>()
    people1.add(Person("Alice", 31))
    people1.add(Person("Bob", 45))
    val items1 = mutableListOf<Item>()
    items1.add(Gold(500))
    val house1 = House(people1, items1)
    houses.add(house1)


    val people2 = mutableListOf<Person>()
    val items2 = mutableListOf<Item>()
    items2.add(Sword(24.2))
    items2.add(Sword(16.7))
    items2.add(Shield(15.3))
    val house2 = House(people2, items2)
    houses.add(house2)


    val people3 = mutableListOf<Person>()
    val items3 = mutableListOf<Item>()
    val house3 = House(people3, items3)
    houses.add(house3)


    val people4 = mutableListOf<Person>()
    people4.add(Person("Charles", 52))
    val items4 = mutableListOf<Item>()
    items4.add(Gold(2500))
    items4.add(Sword(0.0))
    items4.add(Shield(0.0))
    val house4 = House(people4, items4)
    houses.add(house4)


    val village = Village(houses)

    print(village)

}
