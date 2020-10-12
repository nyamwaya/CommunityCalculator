package com.nyamwaya.communitycalculator.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyamwaya.communitycalculator.CalculationRequest
import com.nyamwaya.communitycalculator.UserIntent
import com.nyamwaya.communitycalculator.UserState
import com.nyamwaya.communitycalculator.arch.IModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch


class UserViewModel() : ViewModel(),
    IModel<UserState, UserIntent> {

    override val intents: Channel<UserIntent> = Channel(Channel.UNLIMITED)

    private val _state = MutableLiveData<UserState>().apply { value =
        UserState()
    }
    override val state: LiveData<UserState>
        get() = _state

    init {
        handlerIntent()
    }

    private fun handlerIntent() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect {userIntent: UserIntent ->
                when(userIntent) {
                    is UserIntent.NumberPressed ->{
                        calculate(userIntent.calculationRequest)
                    }

                }
            }
        }
    }


    private fun calculate(calculationRequest: CalculationRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                updateState { it.copy(result = calculationRequest.firstNumber.toString() ) }
                //updateState { it.copy(isLoading = false, users = userApi.getUser()) }
            } catch (e: Exception) {
                Log.e("Aleckson", "error: ${e.toString()}")
               // updateState { it.copy(isLoading = false, errorMessage = e.message) }
            }
        }
    }

    private suspend fun updateState(handler: suspend (intent: UserState) -> UserState) {
        _state.postValue(handler(state.value!!))
    }
}