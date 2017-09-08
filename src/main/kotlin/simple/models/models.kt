package simple.models

data class Person(val name: String, val age: Int)

data class House(val people: List<Person>)

data class Village(val houses: List<House>)
