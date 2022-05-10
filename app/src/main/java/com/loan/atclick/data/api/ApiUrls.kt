package com.app.grofiesta.data.api

import com.app.grofiesta.data.model.ApiResponseModels
import com.loan.atclick.data.model.request.ApplyLoan
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

object ApiUrls {
    const val BASE_URL = "https://loanatclick.com/api/"

    
    const val LOGIN = "login"
    const val REGISTRATION = "register"
    const val VERIFY_OTP = "validate-otp"
    const val UPDATE_PROFILE = "profile"
    const val SEND_REFER = "refer-earn"
    const val GET_LOAN_TYPE = "get-loan-type"
    const val GET_MY_REFERAL = "refer-earn"
    const val GET_ALL_FILEDS = "apply-loan-variable-fields"
    const val APPLY_LOAN = "apply-loan"
    const val MY_APP_STATUS = "get-loan-status"
    const val MY_APP_STATUS_TRACK = "track-loan"
    const val MY_APP_STATUS_DETAIL = "loan-details"
    const val ADD_COMPLAINT = "feedback-comlpaint"
    const val ADD_LOAN_ENQ = "loan-enquiry"
    const val CHANGE_PIN = "change-pin"
    const val FORGOT_PIN = "forgot-pin"
    const val DASHBOARD = "promotional-image"

}