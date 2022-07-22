package com.renan.conversordemedidas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.renan.conversordemedidas.model.CalculationStrategyHolder
import com.renan.conversordemedidas.model.strategies.*

class MainActivity : AppCompatActivity() {

    val supportedCalculationStrategies = arrayOf(
        CalculationStrategyHolder("Centimetro para Quilometro",CentimeterToKilometerStrategy()),
        CalculationStrategyHolder("Quilometro para Centimetro",KilometerToCentimeterStrategy()),
        CalculationStrategyHolder("Quilometro para Metro",KilometerToMeterStrategy()),
        CalculationStrategyHolder("Metro para Centimetro",MeterToCentimeterStrategy()),
        CalculationStrategyHolder("Metro para Quilometro", MeterToKilometerStrategy())

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        
    }
}