package com.nyamwaya.communitycalculator.di

import com.nyamwaya.communitycalculator.viewmodel.UserViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelScope = module {
    viewModel { UserViewModel() }
}