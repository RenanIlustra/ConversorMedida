package com.renan.conversordemedidas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.renan.conversordemedidas.model.CalculationStrategyHolder
import com.renan.conversordemedidas.model.Calculator
import com.renan.conversordemedidas.model.strategies.*
import java.lang.NumberFormatException
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {

    private lateinit var edtValue: EditText
    private lateinit var spConversions: Spinner

    private val supportedCalculationStrategies = arrayOf(
        CalculationStrategyHolder("Centimetro para Quilometro", CentimeterToKilometerStrategy()),
        CalculationStrategyHolder("Quilometro para Centimetro", KilometerToCentimeterStrategy()),
        CalculationStrategyHolder("Quilometro para Metro", KilometerToMeterStrategy()),
        CalculationStrategyHolder("Metro para Centimetro", MeterToCentimeterStrategy()),
        CalculationStrategyHolder("Metro para Quilometro", MeterToKilometerStrategy())

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var value  = 0.0
        var position = 0

        savedInstanceState?.let {
            value = it.getDouble("VALUE")
            position = it.getInt("POSITION")
        }

        unitUi()
        setUi(position,value)
        setActions()

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        try {
            outState.putDouble("VALUE", edtValue.text.toString().toDouble())

        }catch (e : NumberFormatException){

        }
        outState.putInt("POSITION", spConversions.selectedItemPosition)
    }

    private fun unitUi() {
        spConversions = findViewById(R.id.spConversions)
        edtValue = findViewById(R.id.edtValue)
    }

    private fun setActions() {
        val btnConvert: Button = findViewById(R.id.btnConvert)
        val edtValue: EditText = findViewById(R.id.edtValue)

        btnConvert.setOnClickListener {

            //Trata o erro de tentar converter uma String vazia em Double
            try {
                //Valor digitado no EditText
                val value = edtValue.text.toString().toDouble()

                //Criando uma variavel para Guardar a posição selecionada no spinner
                val selectedItemPosition = spConversions.selectedItemPosition

                //Pegando na mutableList criada de StrategyHolders a estrategia da posição selecionada pelo Spinner
                val calculationStrategy =
                    supportedCalculationStrategies[selectedItemPosition].calculationStrategy

                Calculator.setCalculationStrategy(calculationStrategy)

                val result = Calculator.calculate(value)
                val label = calculationStrategy.getResultLabel(result != 1.toDouble())

                showResult(result, label)


            } catch (e: NumberFormatException) {
                edtValue.error = "Valor inválido"
                edtValue.requestFocus()

            }
        }

        val btnClear: Button = findViewById(R.id.btnClear)
        btnClear.setOnClickListener {

            clear()
        }
    }

    private fun clear() {
        edtValue.setText("")
        edtValue.error = null

        spConversions.setSelection(0)
    }

    private fun showResult(result: Double, label: String) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("RESULT", result)
        intent.putExtra("LABEL", label)

        startActivity(intent)
    }

    private fun setUi(position: Int,value :Double) {
        spConversions = findViewById(R.id.spConversions)

        //Configurando e populando o spinner
        val spAdapter = ArrayAdapter(this, R.layout.res_spinner_item, getSpinnerData())
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spConversions.setSelection(position)
        edtValue.setText(value.toString())
        spConversions.adapter = spAdapter
    }

    private fun getSpinnerData(): MutableList<String> {
        val spinnerData = mutableListOf<String>()
        supportedCalculationStrategies.forEach {
            spinnerData.add(it.name)

        }
        return spinnerData
    }
}