package com.nyamwaya.communitycalculator.arch

interface IView<S: IState> {
    fun render(state: S)

}