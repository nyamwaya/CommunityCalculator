package com.nyamwaya.communitycalculator.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.nyamwaya.communitycalculator.CalculationRequest
import com.nyamwaya.communitycalculator.R
import com.nyamwaya.communitycalculator.UserIntent
import com.nyamwaya.communitycalculator.UserState
import com.nyamwaya.communitycalculator.arch.IView
import com.nyamwaya.communitycalculator.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.NullPointerException

class MainActivity : AppCompatActivity(),
    IView<UserState> {
    private val mViewModel by viewModel<UserViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Observing the state
        mViewModel.state.observe(this, Observer {
            render(it)
        })

        // Refresh data
        one.setOnClickListener {
            lifecycleScope.launch {
                mViewModel.intents.send(UserIntent.NumberPressed( CalculationRequest(
                    firstNumber = one.text.toString().toInt(),
                    secondNumber = 0,
                    operation = "Add"
                )))
            }
        }
    }

    override fun render(state: UserState) {
        with(state) {

            resultTextView.text = result



            try {

            }catch (e: NullPointerException){
                Log.e("Aleckson", "NPE: $e")
            }
        }
    }

}

