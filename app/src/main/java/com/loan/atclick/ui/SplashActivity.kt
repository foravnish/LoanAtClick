package com.loan.atclick.ui

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.accountapp.accounts.utils.Prefences
import com.loan.atclick.R
import com.loan.atclick.base.BaseActivity
import com.loan.atclick.databinding.ActivityMainBinding
import com.loan.atclick.home.HomeActivity
import com.loan.atclick.login.LoginActivity
import com.loan.atclick.ui.splash.SplashHandler
import com.loan.atclick.ui.splash.SplashRepository
import com.loan.atclick.utils.Utility

class SplashActivity : BaseActivity() , SplashHandler {

    lateinit var binding: ActivityMainBinding
    internal var mSplashRepo = SplashRepository(this)
    val mContext by lazy { this@SplashActivity }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSplashRepo.onSplashInitiated()

    }

    override fun onSplashCompleted() {

        val isUserLoggedIn = Prefences.getIsLogin(mContext)

        if (isUserLoggedIn)
            Utility.startActivityWithLeftToRightAnimation(
                this,
                Intent(this, HomeActivity::class.java)
            )

        else
            Utility.startActivityWithLeftToRightAnimation(
                this,
                Intent(this, LoginActivity::class.java)
            )
        finish()

    }}