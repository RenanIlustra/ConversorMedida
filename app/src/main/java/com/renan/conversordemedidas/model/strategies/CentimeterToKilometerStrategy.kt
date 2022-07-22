package com.renan.conversordemedidas.model.strategies

class CentimeterToKilometerStrategy: CalculationStrategy {
    override fun calculate(value: Double): Double {
        return value / 100_000
    }

    override fun getResultLabel(isPlural: Boolean): String = if (isPlural) "kilometros" else "kilometro"
}