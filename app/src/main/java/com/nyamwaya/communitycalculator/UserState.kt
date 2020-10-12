package com.nyamwaya.communitycalculator

import com.nyamwaya.communitycalculator.arch.IState

data class UserState (
    val result: String? = null
): IState