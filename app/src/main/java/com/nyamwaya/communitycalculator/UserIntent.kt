package com.nyamwaya.communitycalculator

sealed class UserIntent: IIntent {
    object Add : UserIntent()
    object GenerateResults : UserIntent()
}