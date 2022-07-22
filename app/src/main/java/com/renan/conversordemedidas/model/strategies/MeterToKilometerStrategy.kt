package com.renan.conversordemedidas.model.strategies

class MeterToKilometerStrategy: CalculationStrategy {

    override fun calculate(value: Double): Double {
            return value / 1_000
    }

    override fun getResultLabel(isPlural: Boolean): String = if (isPlural) "kilometros" else "kilometro"
}