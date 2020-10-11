package com.nyamwaya.communitycalculator

interface IView<S: IState> {
    fun render(state: S)

}