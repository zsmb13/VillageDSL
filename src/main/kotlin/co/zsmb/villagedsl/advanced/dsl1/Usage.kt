package co.zsmb.villagedsl.advanced.dsl1

fun main(args: Array<String>) {

    val v = village {
        house {
            person {
                name = "Alice"
                age = 31
            }
            person {
                name = "Bob"
                age = 45
            }
            gold {
                amount = 500
            }
        }
        house {
            sword {
                strength = 24.2
            }
            sword {
                strength = 16.7
            }
            shield {
                defense = 15.3
            }
        }
        house()
        house {
            gold {
                amount = 2500
            }
            sword()
            shield {}
            person {
                name = "Charles"
                age = 52
            }
        }
    }

    print(v)

}
