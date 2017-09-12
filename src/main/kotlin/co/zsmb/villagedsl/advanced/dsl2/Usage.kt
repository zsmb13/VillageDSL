package co.zsmb.villagedsl.advanced.dsl2

fun main(args: Array<String>) {

    val v = village {
        house {
            "Alice" age 31
            "Bob" age 45
            500.gold
        }
        house {
            sword with strength value 24.2
            sword with strength level 16.7
            shield with defense value 15.3
        }
        house()
        house {
            "Charles" age 52
            2500.gold
            sword
            shield
        }
    }

    print(v)

}
