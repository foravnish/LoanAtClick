package com.loan.atclick.login

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.accountapp.accounts.utils.Prefences
import com.app.grofiesta.data.apiClient.ApiClient
import com.app.grofiesta.data.apiClient.ApiInterface
import com.app.grofiesta.data.apiClient.NetworkHandling
import com.app.grofiesta.data.model.ApiResponseModels
import com.loan.atclick.base.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import network.AppRetrofit
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginResposatory {

    private var mRepository: LoginResposatory? = null

    fun getInstance(): LoginResposatory {
        if (mRepository == null) {
            mRepository = LoginResposatory()
        }
        return mRepository as LoginResposatory
    }

    private var apiInterface: ApiInterface? = null

    constructor() {
        apiInterface = ApiClient.createService(ApiInterface::class.java)
    }


    @SuppressLint("CheckResult")
    fun getLogin(
        context: Context, emailId: String,pin:String, showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.LoginResponse> {
        val mLiveData = MutableLiveData<ApiResponseModels.LoginResponse>()
        if (NetworkHandling.isConnected(context)) {
            if (showDialog) (context as BaseActivity).showDialog()
            apiInterface!!.getLogin(emailId,pin)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnError {
                    (context as BaseActivity).dismissDialog()
                    NetworkHandling.showNetworkError(context, it)
                }.subscribe({
                    try {
                        (context as BaseActivity).dismissDialog()
                        mLiveData.value = it
                    } catch (e: Exception) {
                        println(e.printStackTrace())
                    }
                }, { error ->
                })
        } else {
           // NetworkHandling.getRetryDialog(context, RetryDialog.NO_INTERNET)
        }
        return mLiveData
    }

    @SuppressLint("CheckResult")
    fun getRegistration(
        context: Context, name: String,phone:String, address:String,email:String,pin:String,showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.RegistrationResponse> {
        val mLiveData = MutableLiveData<ApiResponseModels.RegistrationResponse>()
        if (NetworkHandling.isConnected(context)) {
            if (showDialog) (context as BaseActivity).showDialog()
            apiInterface!!.getRegistration(name,phone,address,email ,pin)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnError {
                    (context as BaseActivity).dismissDialog()
                    NetworkHandling.showNetworkError(context, it)
                }.subscribe({
                    try {
                        (context as BaseActivity).dismissDialog()
                        mLiveData.value = it
                    } catch (e: Exception) {
                        println(e.printStackTrace())
                    }
                }, { error ->
                })
        } else {
            // NetworkHandling.getRetryDialog(context, RetryDialog.NO_INTERNET)
        }
        return mLiveData
    }

    @SuppressLint("CheckResult")
    fun getOtpVerfy(
        context: Context, phone: String,otp:String, enc_otp:String,showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.LoginResponse> {
        val mLiveData = MutableLiveData<ApiResponseModels.LoginResponse>()
        if (NetworkHandling.isConnected(context)) {
            if (showDialog) (context as BaseActivity).showDialog()
            apiInterface!!.getOtpVerfy(phone,otp,enc_otp)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnError {
                    (context as BaseActivity).dismissDialog()
                    NetworkHandling.showNetworkError(context, it)
                }.subscribe({
                    try {
                        (context as BaseActivity).dismissDialog()
                        mLiveData.value = it
                    } catch (e: Exception) {
                        println(e.printStackTrace())
                    }
                }, { error ->
                })
        } else {
            // NetworkHandling.getRetryDialog(context, RetryDialog.NO_INTERNET)
        }
        return mLiveData
    }

    @SuppressLint("CheckResult")
    fun getChangePin(
        context: Context, new_pin: String,re_pin:String, showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.LoginResponse> {
        val mLiveData = MutableLiveData<ApiResponseModels.LoginResponse>()
        if (NetworkHandling.isConnected(context)) {
            if (showDialog) (context as BaseActivity).showDialog()
            apiInterface!!.getChangePin(Prefences.getUserId(context)!!,new_pin,re_pin)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnError {
                    (context as BaseActivity).dismissDialog()
                    NetworkHandling.showNetworkError(context, it)
                }.subscribe({
                    try {
                        (context as BaseActivity).dismissDialog()
                        mLiveData.value = it
                    } catch (e: Exception) {
                        println(e.printStackTrace())
                    }
                }, { error ->
                })
        } else {
            // NetworkHandling.getRetryDialog(context, RetryDialog.NO_INTERNET)
        }
        return mLiveData
    }


    @SuppressLint("CheckResult")
    fun getForgotPin(
        context: Context, emailId: String,showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.LoginResponse> {
        val mLiveData = MutableLiveData<ApiResponseModels.LoginResponse>()
        if (NetworkHandling.isConnected(context)) {
            if (showDialog) (context as BaseActivity).showDialog()
            apiInterface!!.getForgotPin(emailId)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnError {
                    (context as BaseActivity).dismissDialog()
                    NetworkHandling.showNetworkError(context, it)
                }.subscribe({
                    try {
                        (context as BaseActivity).dismissDialog()
                        mLiveData.value = it
                    } catch (e: Exception) {
                        println(e.printStackTrace())
                    }
                }, { error ->
                })
        } else {
            // NetworkHandling.getRetryDialog(context, RetryDialog.NO_INTERNET)
        }
        return mLiveData
    }

    @SuppressLint("CheckResult")
    fun updateProfile(
        context: Context, name: RequestBody, phone:RequestBody, image: MultipartBody.Part?, showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.LoginResponse> {
        val mLiveData = MutableLiveData<ApiResponseModels.LoginResponse>()
        if (NetworkHandling.isConnected(context)) {
            if (showDialog) (context as BaseActivity).showDialog()
            apiInterface!!.updateProfile(Prefences.getUserId(context)!!,name,phone,image)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnError {
                    (context as BaseActivity).dismissDialog()
                    NetworkHandling.showNetworkError(context, it)
                }.subscribe({
                    try {
                        (context as BaseActivity).dismissDialog()
                        mLiveData.value = it
                    } catch (e: Exception) {
                        println(e.printStackTrace())
                    }
                }, { error ->
                })
        } else {
            // NetworkHandling.getRetryDialog(context, RetryDialog.NO_INTERNET)
        }
        return mLiveData
    }

    @SuppressLint("CheckResult")
    fun getProfile(
        context: Context, showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.Profile> {
        val mLiveData = MutableLiveData<ApiResponseModels.Profile>()
        if (NetworkHandling.isConnected(context)) {
            if (showDialog) (context as BaseActivity).showDialog()
            apiInterface!!.getProfile(Prefences.getUserId(context)!!)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnError {
                    (context as BaseActivity).dismissDialog()
                    NetworkHandling.showNetworkError(context, it)
                }.subscribe({
                    try {
                        (context as BaseActivity).dismissDialog()
                        mLiveData.value = it
                    } catch (e: Exception) {
                        println(e.printStackTrace())
                    }
                }, { error ->
                })
        } else {
            // NetworkHandling.getRetryDialog(context, RetryDialog.NO_INTERNET)
        }
        return mLiveData
    }

}