package com.loan.atclick.login


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.accountapp.accounts.utils.Prefences
import com.loan.atclick.R
import com.loan.atclick.base.BaseActivity
import com.loan.atclick.databinding.ActivityLoginBinding
import com.loan.atclick.home.HomeActivity
import com.loan.atclick.utils.Utility
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_my_account.*

class LoginActivity : BaseActivity() {

    lateinit var binding: ActivityLoginBinding
    val mContext by lazy { this@LoginActivity }
    lateinit var mViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mViewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(LoginViewModel::class.java)
        mViewModel.init(this@LoginActivity)


    }

    fun clickForgotPin(view:View){
        Intent(this@LoginActivity, ForgotPasswordActivity::class.java).apply {
        }.let {
            Utility.startActivityWithLeftToRightAnimation(this@LoginActivity,it)
        }

    }
    fun goToOtp(view: View){

        hideKeyboard()
        if (editEmailId.text.toString().isEmpty())
            showAlert("Please enter Email Id")
        else if (editPin.text.toString().isEmpty())
            showAlert("Please enter Pin")
        else{
            callLoginApi()

        }

    }

    fun goToRegister(view:View){
        Utility.startActivityWithLeftToRightAnimation(
            this,
            Intent(this, RegistrationActivity::class.java)
        )
    }

    private fun callLoginApi() {
        mViewModel.initLogin(editEmailId.text.toString(),editPin.text.toString(), true)!!.observe(this, Observer {
            hideKeyboard()
            if (it.success) {
                Prefences.setUserId(mContext,""+it.user_id)

                callProfileData()


            } else
                showToast(it.message)

        })

    }

    private fun callProfileData() {

        mViewModel.initGetProfile(true)!!.observe(this, Observer {
            hideKeyboard()
            if (it.success) {

                Prefences.setIsLogin(mContext, true)
                Prefences.setName(mContext,""+it.user_data.name)
                Prefences.setUserEmailId(mContext,""+it.user_data.email)
                Prefences.setUserMobile(mContext,""+it.user_data.phone)
                Prefences.setUserAddress(mContext,""+it.user_data.address)
                Prefences.setUserImage(mContext,""+it.user_data.image)

                Intent(this@LoginActivity, HomeActivity::class.java).apply {
                }.let {
                    Utility.startActivityWithLeftToRightAnimation(this@LoginActivity,it)
                }
                finish()


            } else
                showToast(it.message)

        })


    }

//    private fun isValidation(): Boolean {
//        if (TextUtils.isEmpty(binding.etUsername.text.toString())) {
//            showSnackBar(
//                binding.root,
//                getString(R.string.err_user_name)
//            )
//            return false
//        } else if (TextUtils.isEmpty(binding.etPassword.text.toString())) {
//            showSnackBar(
//                binding.root,
//                getString(R.string.err_password)
//            )
//            return false
//        }
//        return true
//    }



}