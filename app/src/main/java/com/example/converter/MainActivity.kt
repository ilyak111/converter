package com.example.converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userChoice = findViewById<RadioGroup>(R.id.radioGroup_user_choice)
        val userInput = findViewById<EditText>(R.id.editText_user_input)
        val evaluateButton = findViewById<Button>(R.id.button_evaluate)
        val output = findViewById<TextView>(R.id.textView_output)
        val outputUnits = findViewById<TextView>(R.id.textView_output_units)

        fun convertCelsiusToFahrenheit(valueInCelsius: Float): Float = valueInCelsius * 1.8f + 32f
        fun convertCelsiusToKelvin(valueInCelsius: Float): Float = valueInCelsius + 273.15f
        fun checkTemperature(valueInCelsius: Float) = valueInCelsius >= -273.15f
        fun outputConversion() {
            val enteredTemperature = userInput.text.toString().toFloat()
            if (!checkTemperature(enteredTemperature))
                output.text = "Температура ниже абсолютного нуля"
            else {
                when (userChoice.checkedRadioButtonId) {
                    R.id.radioButton_fahrenheit -> output.text = convertCelsiusToFahrenheit(enteredTemperature).toString()
                    R.id.radioButton_kelvin -> output.text = convertCelsiusToKelvin(enteredTemperature).toString()
                }
            }
        }
        fun tryToOuputConversion() {
            if (userInput.text.isBlank())
                output.text = ""
            else
                outputConversion()
        }

        userChoice.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButton_fahrenheit -> outputUnits.text = getString(R.string.textView_output_units_if_fahrenheit_text)
                R.id.radioButton_kelvin -> outputUnits.text = getString(R.string.textView_output_units_if_kelvin_text)
            }
            tryToOuputConversion()
        }
        evaluateButton.setOnClickListener {
            tryToOuputConversion()
        }
    }
}