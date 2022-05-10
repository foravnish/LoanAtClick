package com.loan.atclick.ui.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.loan.atclick.R
import com.loan.atclick.base.BaseActivity
import com.loan.atclick.login.LoginViewModel
import kotlinx.android.synthetic.main.activity_change_pin.*
import kotlinx.android.synthetic.main.app_header_layout.*

class ChangePinActivity : BaseActivity() {
    lateinit var mViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pin)

        mViewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(LoginViewModel::class.java)
        mViewModel.init(this@ChangePinActivity)

        imgBack.setOnClickListener { finish() }
        txtPageTitle.text = "Change Pin"


        
    }

    fun clickChangePin(view: View){
        if (editPin.text.toString().isEmpty())
            showToast("Please enter New pin")
        else if (editPin.text.toString().length<6)
            showToast("Please enter 6 digit pin")
        else if (edtConfPin.text.toString().isEmpty())
            showToast("Please enter Confirm pin")
        else if (edtConfPin.text.toString().length<6)
            showToast("Please enter 6 digit Confirm pin")
        else if (!editPin.text.toString().equals(edtConfPin.text.toString()))
            showToast("New pin and Confirm pin is mismatch")
        else {
            hideKeyboard()
            mViewModel.initChangePin(editPin.text.toString(), edtConfPin.text.toString(), true)!!
                .observe(this, Observer {

                    if (it.success) {

                        showToast(it.message)
                        finish()

                    } else
                        showToast(it.message)

                })
        }

    }

}