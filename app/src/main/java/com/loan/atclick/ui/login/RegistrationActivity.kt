package com.loan.atclick.login


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.accountapp.accounts.utils.Prefences
import com.loan.atclick.R
import com.loan.atclick.base.BaseActivity
import com.loan.atclick.ui.login.OTPActivity
import com.loan.atclick.utils.Utility
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_registration.editEmailId
import kotlinx.android.synthetic.main.activity_registration.editPin

class RegistrationActivity : BaseActivity() {

    val mContext by lazy { this@RegistrationActivity }
    lateinit var mViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        mViewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(LoginViewModel::class.java)
        mViewModel.init(this@RegistrationActivity)


    }

    fun goToRegister(view:View){
        hideKeyboard()
        if (editName.text.toString().isEmpty())
            showAlert("Please Enter Name")
        else if (editPhone.text.toString().isEmpty())
            showAlert("Please Enter Mobile no")
        else if (editAddress.text.toString().isEmpty())
            showAlert("Please Enter Address")
        else if (editEmailId.text.toString().isEmpty())
            showAlert("Please Enter Email Id")
        else if (editPin.text.toString().isEmpty())
            showAlert("Please Enter Pin")
        else{
            callRegistrationApi()
        }

    }

    private fun callRegistrationApi() {

        mViewModel.initRegistration(editName.text.toString(),editPhone.text.toString(),
            editAddress.text.toString(),editEmailId.text.toString(),
            editPin.text.toString(),
            true)!!.observe(this, Observer {
            hideKeyboard()
            if (it.success) {

                Intent(this, OTPActivity::class.java).apply {
                    putExtra("mobile",editPhone.text.toString().trim())
                    putExtra("enc_otp",it.enc_otp)

                }.let {
                    Utility.startActivityWithLeftToRightAnimation(this,it)
                }

            } else
                showToast(it.message)

        })

    }

}