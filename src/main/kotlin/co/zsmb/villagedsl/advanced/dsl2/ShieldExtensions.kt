package co.zsmb.villagedsl.advanced.dsl2

import co.zsmb.villagedsl.advanced.models.Shield

class ShieldContinuation(val house: HouseBuilder)

@AdvancedDSL2
object defense

@AdvancedDSL2
val HouseBuilder.shield: ShieldContinuation
    get() {
        this.items += Shield(0.0)
        return ShieldContinuation(this)
    }

@AdvancedDSL2
infix fun ShieldContinuation.with(d: defense): ShieldBuilder {
    house.items.removeAt(house.items.lastIndex)
    return ShieldBuilder(house)
}

class ShieldBuilder(val house: HouseBuilder)

@AdvancedDSL2
infix fun ShieldBuilder.value(defense: Double) {
    house.items += Shield(defense)
}
