package com.loan.atclick.login

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.app.grofiesta.data.model.ApiResponseModels
import okhttp3.MultipartBody
import okhttp3.RequestBody

class LoginViewModel(application: Application): AndroidViewModel(application) {
    private var mRepo: LoginResposatory? = null

    private lateinit var mContext: Context

    fun init(context: Context) {
        this.mContext = context
    }


    fun initLogin(
        emailId: String, pin:String,showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.LoginResponse>? {
        mRepo = LoginResposatory().getInstance()
        mRepo!!.getLogin(mContext, emailId,pin, showDialog)
            .let { return it }
    }
    fun initRegistration(
        name: String,phone:String, address:String,email:String,pin:String,showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.RegistrationResponse>? {
        mRepo = LoginResposatory().getInstance()
        mRepo!!.getRegistration(mContext, name,phone,address,email ,pin, showDialog)
            .let { return it }
    }
    fun initVerfiOtp(
        phone: String, otp:String,enc_otp:String,showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.LoginResponse>? {
        mRepo = LoginResposatory().getInstance()
        mRepo!!.getOtpVerfy(mContext, phone,otp,enc_otp, showDialog)
            .let { return it }
    }


    fun initChangePin(
        new_pin: String, re_pin:String,showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.LoginResponse>? {
        mRepo = LoginResposatory().getInstance()
        mRepo!!.getChangePin(mContext, new_pin,re_pin, showDialog)
            .let { return it }
    }
    fun getForgotPin(
        emailId: String, showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.LoginResponse>? {
        mRepo = LoginResposatory().getInstance()
        mRepo!!.getForgotPin(mContext, emailId, showDialog)
            .let { return it }
    }


    fun initUpdateProfile(
        name: RequestBody, phone: RequestBody, image: MultipartBody.Part?, showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.LoginResponse>? {
        mRepo = LoginResposatory().getInstance()
        mRepo!!.updateProfile(mContext, name,phone, image,showDialog)
            .let { return it }
    }

    fun initGetProfile(
        showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.Profile>? {
        mRepo = LoginResposatory().getInstance()
        mRepo!!.getProfile(mContext, showDialog)
            .let { return it }
    }

}