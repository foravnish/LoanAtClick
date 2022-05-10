package com.loan.atclick.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.accountapp.accounts.utils.Prefences
import com.bumptech.glide.Glide
import com.loan.atclick.R
import com.loan.atclick.base.BaseActivity
import com.loan.atclick.login.LoginActivity
import com.loan.atclick.login.LoginViewModel
import com.loan.atclick.ui.loan.ApplicationFormActivity
import com.loan.atclick.utils.Utility
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_my_account.*
import kotlinx.android.synthetic.main.app_header_layout.*

class MyAccountActivity : BaseActivity() {

    val mContext by lazy { this@MyAccountActivity }
    lateinit var mViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_account)
        imgBack.setOnClickListener { finish() }
        txtPageTitle.text = "My Profile"

        mViewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(LoginViewModel::class.java)
        mViewModel.init(this@MyAccountActivity)

        callProfileData()

    }

    private fun callProfileData() {

        mViewModel.initGetProfile(true)!!.observe(this, Observer {
            hideKeyboard()
            if (it.success) {

                Prefences.setName(mContext,""+it.user_data.name)
                Prefences.setUserEmailId(mContext,""+it.user_data.email)
                Prefences.setUserMobile(mContext,""+it.user_data.phone)
                Prefences.setUserAddress(mContext,""+it.user_data.address)
                Prefences.setUserImage(mContext,""+it.user_data.image)

                txtUserName.text=""+it.user_data.name
                txtUserEmail.text=""+it.user_data.email
                txtErpId.text=""+it.user_data.id
                txtMobile.text=""+it.user_data.phone
                txtAddress.text=""+it.user_data.address

                Glide.with(this).load(it.user_data.image).error(R.drawable.ic_user_dummy).circleCrop().into(imgProfilePic!!)

            } else
                showToast(it.message)

        })


    }

    fun clickEditProfile(view: View){


        Intent(this@MyAccountActivity, EditProfileActivity::class.java).apply {
        }.let {
            startActivityForResult(it,101)
        }

    }


    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101) {
            callProfileData()
        }
    }


}
