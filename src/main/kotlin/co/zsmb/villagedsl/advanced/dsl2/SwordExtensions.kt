package co.zsmb.villagedsl.advanced.dsl2

import co.zsmb.villagedsl.advanced.models.Sword

class SwordContinuation(val house: HouseBuilder)

object strength

val HouseBuilder.sword: SwordContinuation
    get() {
        this.items += Sword(0.0)
        return SwordContinuation(this)
    }

infix fun SwordContinuation.with(d: strength): SwordBuilder {
    house.items.removeAt(house.items.lastIndex)
    return SwordBuilder(house)
}

class SwordBuilder(val house: HouseBuilder)

infix fun SwordBuilder.value(strength: Double) {
    house.items += Sword(strength)
}

infix fun SwordBuilder.level(strength: Double) = value(strength)
