package com.renan.conversordemedidas.model.strategies

class MeterToCentimeterStrategy : CalculationStrategy {

    override fun calculate(value: Double): Double {
        return value / 100
    }

    override fun getResultLabel(isPlural: Boolean): String = if (isPlural) "centimetros" else "centimetro"

}