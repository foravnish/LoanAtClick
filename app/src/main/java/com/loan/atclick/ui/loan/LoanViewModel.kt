package com.loan.atclick.login

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.app.grofiesta.data.model.ApiResponseModels
import com.loan.atclick.data.model.request.ApplyLoan
import com.loan.atclick.data.model.request.LoanEnq

class LoanViewModel(application: Application): AndroidViewModel(application) {
    private var mRepo: LoanResposatory? = null

    private lateinit var mContext: Context

    fun init(context: Context) {
        this.mContext = context
    }


    fun initSendReferl(name:String,
        emailId: String, phone:String,loanType:String,edtAmount:String,showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.LoginResponse>? {
        mRepo = LoanResposatory().getInstance()
        mRepo!!.sendReferal(mContext,name, emailId,phone, loanType,edtAmount,showDialog)
            .let { return it }
    }

    fun initGetLoanType(showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.LoanType>? {
        mRepo = LoanResposatory().getInstance()
        mRepo!!.getLoanType(mContext,showDialog)
            .let { return it }
    }

    fun initGetMyReferal(showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.MyReferalResponse>? {
        mRepo = LoanResposatory().getInstance()
        mRepo!!.getMyReferal(mContext,showDialog)
            .let { return it }
    }

    fun initAllFields(showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.AllFieldResponse>? {
        mRepo = LoanResposatory().getInstance()
        mRepo!!.getAllFields(mContext,showDialog)
            .let { return it }
    }

    fun initApplyLoan(req:ApplyLoan,showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.ApplyLoanResponse>? {
        mRepo = LoanResposatory().getInstance()
        mRepo!!.getApplyLoan(mContext, req,showDialog)
            .let { return it }
    }

    fun initGetMyAppStatus(showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.MyAppStatusResponse>? {
        mRepo = LoanResposatory().getInstance()
        mRepo!!.getMyAppStatus(mContext,showDialog)
            .let { return it }
    }
    fun initGetMyAppStatusTrack(id:String,showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.LoanTrackResponse>? {
        mRepo = LoanResposatory().getInstance()
        mRepo!!.getMyAppStatusTrack(mContext,id,showDialog)
            .let { return it }
    }

    fun getMyAppStatusDetail(id:String,showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.LoanDetailResponse>? {
        mRepo = LoanResposatory().getInstance()
        mRepo!!.getMyAppStatusDetail(mContext,id,showDialog)
            .let { return it }
    }

    fun initAddCom(type:String,subject:String,message:String,showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.ApplyLoanResponse>? {
        mRepo = LoanResposatory().getInstance()
        mRepo!!.getAddComp(mContext, type,subject,message,showDialog)
            .let { return it }
    }

    fun initAddLoanEnq(req: LoanEnq, showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.ApplyLoanResponse>? {
        mRepo = LoanResposatory().getInstance()
        mRepo!!.getAddLoanEnq(mContext, req,showDialog)
            .let { return it }
    }

    fun initGetDashboard( showDialog: Boolean
    ): MutableLiveData<ApiResponseModels.ApplyLoanResponse>? {
        mRepo = LoanResposatory().getInstance()
        mRepo!!.getDashboard(mContext,showDialog)
            .let { return it }
    }

}