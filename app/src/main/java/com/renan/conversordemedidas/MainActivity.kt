package com.renan.conversordemedidas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.renan.conversordemedidas.model.CalculationStrategyHolder
import com.renan.conversordemedidas.model.Calculator
import com.renan.conversordemedidas.model.strategies.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    private lateinit var spConversions: Spinner

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

        unitUi()
        setUi()
        setActions()

    }

    private fun unitUi() {
        spConversions = findViewById(R.id.spConversions)

    }

    private fun setActions() {
        val btnConvert : Button = findViewById(R.id.btnConvert)
        val edtValue : EditText = findViewById(R.id.edtValue)

        btnConvert.setOnClickListener {

            try {
                //Valor digitado no EditText
                val value = edtValue.text.toString().toDouble()

                //Criando uma variavel para Guardar a posição selecionada no spinner
                val selectedItemPosition = spConversions.selectedItemPosition

                //Pegando na mutableList criada de StrategyHolders a estrategia da posição selecionada pelo Spinner
                val calculationStrategy = supportedCalculationStrategies[selectedItemPosition]

                Calculator.setCalculationStrategy(calculationStrategy.calculationStrategy)

                Calculator.calculate(value)


            }catch (e:NumberFormatException){
                edtValue.error = "Valor inválido"
                edtValue.requestFocus()

            }
        }

    }

    private fun setUi() {
        spConversions = findViewById(R.id.spConversions)
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