package co.zsmb.villagedsl.simple.dsl2

import co.zsmb.villagedsl.simple.models.Person

fun main() {

    val v = village {
        house {
            +Person("Emily", 31)
            +Person("Hannah", 27)
            +Person("Alex", 21)
            +Person("Daniel", 17)
        }
        house {
            +Person("Joe", 48)
        }
        house()
        house {
            -Person("Sarah", 40)
            -Person("Tom", 26)
            -Person("Holly", 52)
        }
    }

    print(v)

}
