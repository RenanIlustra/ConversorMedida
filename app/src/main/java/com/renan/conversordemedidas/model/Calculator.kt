package com.renan.conversordemedidas.model

import com.renan.conversordemedidas.model.strategies.CalculationStrategy

object Calculator {

    private var calculationStrategy: CalculationStrategy? = null

    //Defene qual estrategia de calculo sera usada
    fun setCalculationStrategy(calculationStrategy: CalculationStrategy){
        this.calculationStrategy = calculationStrategy
    }

    fun calculate(value: Double):Double{
        if (calculationStrategy == null)
            throw IllegalAccessException("Calculation Strategi is not set")

        return calculationStrategy!!.calculate(value)
    }

}