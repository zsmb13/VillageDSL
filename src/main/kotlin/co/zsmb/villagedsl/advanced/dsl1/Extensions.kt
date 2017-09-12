package co.zsmb.villagedsl.advanced.dsl1

import co.zsmb.villagedsl.advanced.models.Gold
import co.zsmb.villagedsl.advanced.models.Item
import co.zsmb.villagedsl.advanced.models.Shield
import co.zsmb.villagedsl.advanced.models.Sword

class GoldBuilder(initialAmount: Int) {
    var amount = initialAmount

    fun build(): Item {
        return Gold(amount)
    }
}

fun HouseBuilder.gold(amount: Int = 0, setup: GoldBuilder.() -> Unit) {
    val builder = GoldBuilder(amount)
    builder.setup()
    items += builder.build()
}

class SwordBuilder(initialStrength: Double) {
    var strength = initialStrength

    fun build(): Item {
        return Sword(strength)
    }
}

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

fun HouseBuilder.shield(defense: Double = 0.0, setup: ShieldBuilder.() -> Unit = {}) {
    val builder = ShieldBuilder(defense)
    builder.setup()
    items += builder.build()
}
