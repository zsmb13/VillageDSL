package co.zsmb.villagedsl.advanced.dsl1

import co.zsmb.villagedsl.advanced.models.Gold
import co.zsmb.villagedsl.advanced.models.Item
import co.zsmb.villagedsl.advanced.models.Shield
import co.zsmb.villagedsl.advanced.models.Sword

class GoldBuilder(initialAmount: Int) {
    @AdvancedDSL1
    var amount = initialAmount

    fun build(): Item {
        return Gold(amount)
    }
}

@AdvancedDSL1
fun HouseBuilder.gold(amount: Int = 0, setup: GoldBuilder.() -> Unit) {
    val builder = GoldBuilder(amount)
    builder.setup()
    items += builder.build()
}

class SwordBuilder(initialStrength: Double) {
    @AdvancedDSL1
    var strength = initialStrength

    fun build(): Item {
        return Sword(strength)
    }
}

@AdvancedDSL1
fun HouseBuilder.sword(strength: Double = 0.0, setup: SwordBuilder.() -> Unit = {}) {
    val builder = SwordBuilder(strength)
    builder.setup()
    items += builder.build()
}

class ShieldBuilder(initialDefense: Double) {
    var defense = initialDefense

    fun build(): Item {
        return Shield(defense)
    }
}

@AdvancedDSL1
fun HouseBuilder.shield(defense: Double = 0.0, setup: ShieldBuilder.() -> Unit = {}) {
    val builder = ShieldBuilder(defense)
    builder.setup()
    items += builder.build()
}
