package com.renan.conversordemedidas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.renan.conversordemedidas.model.CalculationStrategyHolder
import com.renan.conversordemedidas.model.strategies.*

class MainActivity : AppCompatActivity() {

    private val supportedCalculationStrategies = arrayOf(
        CalculationStrategyHolder("Centimetro para Quilometro",CentimeterToKilometerStrategy()),
        CalculationStrategyHolder("Quilometro para Centimetro",KilometerToCentimeterStrategy()),
        CalculationStrategyHolder("Quilometro para Metro",KilometerToMeterStrategy()),
        CalculationStrategyHolder("Metro para Centimetro",MeterToCentimeterStrategy()),
        CalculationStrategyHolder("Metro para Quilometro", MeterToKilometerStrategy())

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spConversions: Spinner = findViewById(R.id.spConversions)
        //Configurando e populando o spinner
        val spAdapter = ArrayAdapter(this,R.layout.res_spinner_item, getSpinnerData())
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spConversions.adapter = spAdapter
    }

    private fun getSpinnerData():MutableList<String> {
        val spinnerData = mutableListOf<String>()
        supportedCalculationStrategies.forEach {
            spinnerData.add(it.name)

        }
        return spinnerData
    }
}