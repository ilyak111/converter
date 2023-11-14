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

        val converter = Converter()

        val userChoice = findViewById<RadioGroup>(R.id.radioGroup_user_choice)
        val userInput = findViewById<EditText>(R.id.editText_user_input)
        val evaluateButton = findViewById<Button>(R.id.button_evaluate)
        val output = findViewById<TextView>(R.id.textView_output)
        val outputUnits = findViewById<TextView>(R.id.textView_output_units)

        fun outputConversion() {
            if (userInput.text.isBlank())
                output.text = ""
            else {
                val enteredTemperature = userInput.text.toString().toFloat()
                if (!converter.checkTemperature(enteredTemperature))
                    output.text = getString(R.string.textView_output_if_low_text)
                else {
                    when (userChoice.checkedRadioButtonId) {
                        R.id.radioButton_fahrenheit -> output.text = converter.convertCelsiusToFahrenheit(enteredTemperature).toString()
                        R.id.radioButton_kelvin -> output.text = converter.convertCelsiusToKelvin(enteredTemperature).toString()
                    }
                }
            }
        }
        userChoice.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButton_fahrenheit -> outputUnits.text = getString(R.string.textView_output_units_if_fahrenheit_text)
                R.id.radioButton_kelvin -> outputUnits.text = getString(R.string.textView_output_units_if_kelvin_text)
            }
            outputConversion()
        }
        evaluateButton.setOnClickListener {
            outputConversion()
        }
    }
}