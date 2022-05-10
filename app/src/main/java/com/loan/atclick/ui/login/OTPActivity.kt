package com.loan.atclick.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.accountapp.accounts.utils.Prefences
import com.loan.atclick.R
import com.loan.atclick.base.BaseActivity
import com.loan.atclick.home.HomeActivity
import com.loan.atclick.login.LoginActivity
import com.loan.atclick.login.LoginViewModel
import com.loan.atclick.utils.Utility
import kotlinx.android.synthetic.main.activity_o_t_p.*
import kotlinx.android.synthetic.main.app_header_layout.*

class OTPActivity : BaseActivity() {
    var mMobile=""
    var otp=""
    var enc_otp=""
    val mContext by lazy { this@OTPActivity }

    lateinit var mViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_o_t_p)
        mViewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(LoginViewModel::class.java)
        mViewModel.init(this@OTPActivity)


        imgBack.setOnClickListener { finish() }
        txtPageTitle.text = getString(R.string.verify_mobile)

        mMobile=intent.getStringExtra("mobile")!!
        enc_otp=intent.getStringExtra("enc_otp")!!

        countDownOtp()


        if (otp_view.text!!.length == 6) {
            btnVerifyNow.alpha = 1f
            btnVerifyNow.isEnabled = true
            hideKeyboard()
        } else {
            btnVerifyNow.alpha = 0.7f
            btnVerifyNow.isEnabled = false
        }


        otp_view.addTextChangedListener(afterTextChanged = {
            if (otp_view.text!!.length == 6) {
                btnVerifyNow.alpha = 1f
                btnVerifyNow.isEnabled = true
                hideKeyboard()
                verifyOtp()
            } else {
                btnVerifyNow.alpha = 0.7f
                btnVerifyNow.isEnabled = false
            }
        })

    }

    private fun verifyOtp() {

        mViewModel.initVerfiOtp(mMobile,otp_view.text.toString(), ""+enc_otp,true)!!.observe(this, Observer {
            hideKeyboard()
            if (it.success) {

                Utility.startActivityWithLeftToRightAnimation(
                    this,
                    Intent(this, LoginActivity::class.java)
                )

            } else
                showToast(it.message)

        })

    }

    private fun countDownOtp() {
        val timer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                txtTimer.text = "Remain Sec "+((millisUntilFinished / 1000).toString())
            }

            override fun onFinish() {
                txtResendOtp.isEnabled = true
                txtResendOtp.alpha = 1f
            }
        }
        timer.start()
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

                Intent(this@OTPActivity, HomeActivity::class.java).apply {
                }.let {
                    Utility.startActivityWithLeftToRightAnimation(this@OTPActivity,it)
                }
                finish()


            } else
                showToast(it.message)

        })


    }

}