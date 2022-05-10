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
import com.loan.atclick.data.model.request.ApplyLoan
import com.loan.atclick.data.model.request.LoanEnq
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import network.AppRetrofit
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoanResposatory {

    private var mRepository: LoanResposatory? = null

    fun getInstance(): LoanResposatory {
        if (mRepository == null) {
            mRepository = LoanResposatory()
        }
        return mRepository as LoanResposatory
    }

    private var apiInterface: ApiInterface? = null

    constructor() {
        apiInterface = ApiClient.createService(ApiInterface::class.java)
    }


    @SuppressLint("CheckResult")
    fun sendReferal(
        context: Context, name:String,emailId: String,phone:String,loanType:String,edtAmount:String, showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.LoginResponse> {
        val mLiveData = MutableLiveData<ApiResponseModels.LoginResponse>()
        if (NetworkHandling.isConnected(context)) {
            if (showDialog) (context as BaseActivity).showDialog()
            apiInterface!!.sendReferal(Prefences.getUserId(context)!!,name,emailId,phone,loanType,edtAmount)
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
    fun getLoanType(
        context: Context,  showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.LoanType> {
        val mLiveData = MutableLiveData<ApiResponseModels.LoanType>()
        if (NetworkHandling.isConnected(context)) {
            if (showDialog) (context as BaseActivity).showDialog()
            apiInterface!!.getLoanType()
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
    fun getMyReferal(
        context: Context,  showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.MyReferalResponse> {
        val mLiveData = MutableLiveData<ApiResponseModels.MyReferalResponse>()
        if (NetworkHandling.isConnected(context)) {
            if (showDialog) (context as BaseActivity).showDialog()
            apiInterface!!.getMyReferal(Prefences.getUserId(context)!!)
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
    fun getAllFields(
        context: Context,  showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.AllFieldResponse> {
        val mLiveData = MutableLiveData<ApiResponseModels.AllFieldResponse>()
        if (NetworkHandling.isConnected(context)) {
            if (showDialog) (context as BaseActivity).showDialog()
            apiInterface!!.getAllFields()
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
    fun getApplyLoan(
        context: Context, req: ApplyLoan, showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.ApplyLoanResponse> {
        val mLiveData = MutableLiveData<ApiResponseModels.ApplyLoanResponse>()
        if (NetworkHandling.isConnected(context)) {
            if (showDialog) (context as BaseActivity).showDialog()
            apiInterface!!.getApplyLoan(Prefences.getUserId(context)!!,req)
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
    fun getMyAppStatus(
        context: Context,  showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.MyAppStatusResponse> {
        val mLiveData = MutableLiveData<ApiResponseModels.MyAppStatusResponse>()
        if (NetworkHandling.isConnected(context)) {
            if (showDialog) (context as BaseActivity).showDialog()
            apiInterface!!.getMyAppStatus(Prefences.getUserId(context)!!)
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
    fun getMyAppStatusTrack(
        context: Context,id:String,  showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.LoanTrackResponse> {
        val mLiveData = MutableLiveData<ApiResponseModels.LoanTrackResponse>()
        if (NetworkHandling.isConnected(context)) {
            if (showDialog) (context as BaseActivity).showDialog()
            apiInterface!!.getMyAppStatusTrack(id)
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
    fun getMyAppStatusDetail(
        context: Context, id:String, showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.LoanDetailResponse> {
        val mLiveData = MutableLiveData<ApiResponseModels.LoanDetailResponse>()
        if (NetworkHandling.isConnected(context)) {
            if (showDialog) (context as BaseActivity).showDialog()
            apiInterface!!.getMyAppStatusDetail(id)
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
    fun getAddComp(
        context: Context, type:String,subject:String,message:String, showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.ApplyLoanResponse> {
        val mLiveData = MutableLiveData<ApiResponseModels.ApplyLoanResponse>()
        if (NetworkHandling.isConnected(context)) {
            if (showDialog) (context as BaseActivity).showDialog()
            apiInterface!!.getAddComp(Prefences.getUserId(context)!!,type,subject,message)
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
    fun getAddLoanEnq(
        context: Context, req: LoanEnq, showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.ApplyLoanResponse> {
        val mLiveData = MutableLiveData<ApiResponseModels.ApplyLoanResponse>()
        if (NetworkHandling.isConnected(context)) {
            if (showDialog) (context as BaseActivity).showDialog()
            apiInterface!!.getAddLoanEnq(Prefences.getUserId(context)!!,req)
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
    fun getDashboard(
        context: Context,  showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.ApplyLoanResponse> {
        val mLiveData = MutableLiveData<ApiResponseModels.ApplyLoanResponse>()
        if (NetworkHandling.isConnected(context)) {
            if (showDialog) (context as BaseActivity).showDialog()
            apiInterface!!.getDashboard(Prefences.getUserId(context)!!)
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