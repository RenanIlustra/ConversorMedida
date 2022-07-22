package com.renan.conversordemedidas.model

import com.renan.conversordemedidas.model.strategies.CalculationStrategy

data class CalculationStrategyHolder (
    val  name : String,
    val calculationStrategy: CalculationStrategy
)