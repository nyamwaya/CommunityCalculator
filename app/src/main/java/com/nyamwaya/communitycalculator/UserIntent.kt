package com.nyamwaya.communitycalculator

import com.nyamwaya.communitycalculator.arch.IIntent

sealed class UserIntent: IIntent {
    data class NumberPressed(val calculationRequest: CalculationRequest) : UserIntent()
    
    // object NumberPressed : UserIntent()
}