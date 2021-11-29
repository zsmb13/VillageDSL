package co.zsmb.villagedsl.advanced.dsl2

import co.zsmb.villagedsl.advanced.models.Sword

class SwordContinuation(val house: HouseBuilder)

@AdvancedDSL2
object strength

@AdvancedDSL2
val HouseBuilder.sword: SwordContinuation
    get() {
        this.items += Sword(0.0)
        return SwordContinuation(this)
    }

@AdvancedDSL2
infix fun SwordContinuation.with(d: strength): SwordBuilder {
    house.items.removeAt(house.items.lastIndex)
    return SwordBuilder(house)
}

class SwordBuilder(val house: HouseBuilder)

@AdvancedDSL2
infix fun SwordBuilder.value(strength: Double) {
    house.items += Sword(strength)
}

@AdvancedDSL2
infix fun SwordBuilder.level(strength: Double) = value(strength)
