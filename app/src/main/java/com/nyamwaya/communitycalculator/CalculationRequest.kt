package com.nyamwaya.communitycalculator

data class CalculationRequest(
    val firstNumber: Int,
    val operation: String,
    val secondNumber: Int
)