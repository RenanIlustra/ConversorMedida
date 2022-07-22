package com.renan.conversordemedidas.model.strategies

interface CalculationStrategy {

    fun calculate(value:Double):Double

    fun getResultLabel(isPlural : Boolean):String
}