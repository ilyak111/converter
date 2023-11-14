package com.example.converter

class Converter {
    private val ABSOLUTE_ZERO = -273.15f
    fun convertCelsiusToFahrenheit(valueInCelsius: Float): Float = valueInCelsius * 1.8f + 32f
    fun convertCelsiusToKelvin(valueInCelsius: Float): Float = valueInCelsius + 273.15f
    fun checkTemperature(valueInCelsius: Float) = valueInCelsius >= ABSOLUTE_ZERO
}