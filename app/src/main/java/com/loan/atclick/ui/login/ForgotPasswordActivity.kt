package com.loan.atclick.login


import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.loan.atclick.R
import com.loan.atclick.base.BaseActivity
import com.loan.atclick.databinding.ActivityForgotPasswordBinding
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : BaseActivity() {
    lateinit var binding: ActivityForgotPasswordBinding
    val mContext by lazy { this@ForgotPasswordActivity }
    lateinit var mViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        mViewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(LoginViewModel::class.java)
        mViewModel.init(this@ForgotPasswordActivity)

    }


    fun onCLickSubmit(view: View){
        if (editEmailId.text.toString().isEmpty())
            showToast("Please Enter Email Id")
        else {
            hideKeyboard()
            mViewModel.getForgotPin(editEmailId.text.toString(), true)!!
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