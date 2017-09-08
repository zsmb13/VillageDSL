package co.zsmb.villagedsl.simple.dsl1


fun main(args: Array<String>) {

    val v = village {
        house {
            person {
                name = "Emily"
                age = 31
            }
            person(name = "Hannah") {
                age = 27
            }
            person("Alex", 21)
            person(age = 17, name = "Daniel")
        }
        house {
            person {
                name = "Joe"
                age = 48
            }
        }
        house()
        house {
            person {
                name = "Sarah"
                age = 40
            }
            person {
                name = "Tom"
                age = 26
            }
            person {
                name = "Holly"
                age = 52
            }
        }
    }

    print(v)

}
