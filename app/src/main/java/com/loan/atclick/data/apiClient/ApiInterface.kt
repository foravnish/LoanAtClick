package com.app.grofiesta.data.apiClient

import com.app.grofiesta.data.api.ApiUrls
import com.app.grofiesta.data.model.ApiResponseModels
import com.app.grofiesta.data.model.request.*
import com.loan.atclick.data.model.request.ApplyLoan
import com.loan.atclick.data.model.request.LoanEnq
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*


interface ApiInterface {

    @FormUrlEncoded
    @POST(ApiUrls.LOGIN)
    fun getLogin(
        @Field("email") email: String,
        @Field("pin") pin: String
    ): Observable<ApiResponseModels.LoginResponse>

    @FormUrlEncoded
    @POST(ApiUrls.REGISTRATION)
    fun getRegistration(
        @Field("name") name: String,
        @Field("phone") phone: String,
        @Field("address") address: String,
        @Field("email") email: String,
        @Field("pin") pin: String
    ): Observable<ApiResponseModels.RegistrationResponse>

    @FormUrlEncoded
    @POST(ApiUrls.VERIFY_OTP)
    fun getOtpVerfy(
        @Field("phone") phone: String,
        @Field("otp") otp: String,
        @Field("enc_otp") enc_otp: String,
    ): Observable<ApiResponseModels.LoginResponse>

    @FormUrlEncoded
    @POST(ApiUrls.CHANGE_PIN + "/{user_id}")
    fun getChangePin(
        @Path("user_id") id: String,
        @Field("new_pin") new_pin: String,
        @Field("re_pin") re_pin: String
    ): Observable<ApiResponseModels.LoginResponse>

    @FormUrlEncoded
    @POST(ApiUrls.FORGOT_PIN)
    fun getForgotPin(
        @Field("email") email: String,
    ): Observable<ApiResponseModels.LoginResponse>

    @Multipart
    @POST(ApiUrls.UPDATE_PROFILE + "/{user_id}")
    fun updateProfile(
        @Path("user_id") id: String,
        @Part("name") name: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part multipartTypedOutput: MultipartBody.Part?
    ): Observable<ApiResponseModels.LoginResponse>

    @GET(ApiUrls.UPDATE_PROFILE + "/{user_id}")
    fun getProfile(
        @Path("user_id") id: String
    ): Observable<ApiResponseModels.Profile>

    @FormUrlEncoded
    @POST(ApiUrls.SEND_REFER + "/{user_id}")
    fun sendReferal(
        @Path("user_id") id: String,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("ph") ph: String,
        @Field("loan_type") loan_type: String,
        @Field("amount") amount: String
    ): Observable<ApiResponseModels.LoginResponse>

    @GET(ApiUrls.GET_LOAN_TYPE)
    fun getLoanType(
    ): Observable<ApiResponseModels.LoanType>

    @GET(ApiUrls.GET_MY_REFERAL + "/{user_id}")
    fun getMyReferal(
        @Path("user_id") id: String,
    ): Observable<ApiResponseModels.MyReferalResponse>

    @GET(ApiUrls.GET_ALL_FILEDS)
    fun getAllFields(
    ): Observable<ApiResponseModels.AllFieldResponse>

    @POST(ApiUrls.APPLY_LOAN + "/{user_id}")
    fun getApplyLoan(
        @Path("user_id") id: String,
        @Body body: ApplyLoan
    ): Observable<ApiResponseModels.ApplyLoanResponse>

    @GET(ApiUrls.MY_APP_STATUS + "/{user_id}")
    fun getMyAppStatus(
        @Path("user_id") id: String,
    ): Observable<ApiResponseModels.MyAppStatusResponse>

    @GET(ApiUrls.MY_APP_STATUS_TRACK + "/{user_id}")
    fun getMyAppStatusTrack(
        @Path("user_id") id: String,
    ): Observable<ApiResponseModels.LoanTrackResponse>

    @GET(ApiUrls.MY_APP_STATUS_DETAIL + "/{user_id}")
    fun getMyAppStatusDetail(
        @Path("user_id") id: String,
    ): Observable<ApiResponseModels.LoanDetailResponse>

    @FormUrlEncoded
    @POST(ApiUrls.ADD_COMPLAINT + "/{user_id}")
    fun getAddComp(
        @Path("user_id") id: String,
        @Field("type") type: String,
        @Field("subject") subject: String,
        @Field("message") message: String
    ): Observable<ApiResponseModels.ApplyLoanResponse>

    @POST(ApiUrls.ADD_LOAN_ENQ + "/{user_id}")
    fun getAddLoanEnq(
        @Path("user_id") id: String,
        @Body body: LoanEnq
    ): Observable<ApiResponseModels.ApplyLoanResponse>

    @GET(ApiUrls.DASHBOARD + "/{user_id}")
    fun getDashboard(
        @Path("user_id") id: String,
    ): Observable<ApiResponseModels.DashboardBannerResponse>

}