package com.accountapp.accounts.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object Prefences {

    val SHARED_PREF_NAME = "preferences_name"

    lateinit var userId: SharedPreferences
    lateinit var parentId: SharedPreferences
    lateinit var userType: SharedPreferences
    lateinit var isLogin: SharedPreferences
    lateinit var isBalance: SharedPreferences
    lateinit var isExpBalance: SharedPreferences
    lateinit var userName: SharedPreferences
    lateinit var name: SharedPreferences
    lateinit var token: SharedPreferences
    lateinit var userMobile: SharedPreferences
    lateinit var userEmailId: SharedPreferences
    lateinit var userImage: SharedPreferences
    lateinit var userAddress: SharedPreferences

    var USER_ID = "USER_ID"
    var IS_LOGIN = "IS_LOGIN"
    var USER_TYPE = "USER_TYPE"
    var PARENT_ID = "PARENT_ID"
    var IS_BALANCE = "IS_BALANCE"
    var IS_EXP_BAL = "IS_EXP_BAL"
    var USER_NAME = "USER_NAME"
    var NAME = "NAME"
    var TOKEN = "TOKEN"
    var USER_MOBILE = "USER_MOBILE"
    var USER_EMAIL_ID = "USER_EMAIL_ID"
    var USER_IMAGE = "USER_IMAGE"
    var USER_ADDRESS = "USER_ADDRESS"

    @JvmStatic
    fun resetUserData(ctx: Context){
        setIsLogin(ctx,false)
        setUserId(ctx,"")
        setUserEmailId(ctx,"")
        setUserMobile(ctx,"")
        setUserType(ctx,"")
        setToken(ctx,"")
        setUserImage(ctx,"")
    }


    fun setIsBalance(context: Context, iss: Boolean) {
        isBalance = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = isBalance.edit()
        editor.putBoolean(IS_BALANCE, iss)
        editor.commit()
    }

    @JvmStatic
    fun getIsBalance(context: Context): Boolean {
        isBalance = PreferenceManager.getDefaultSharedPreferences(context)
        return isBalance.getBoolean(IS_BALANCE, true)
    }


    fun setIsExpBal(context: Context, iss: Boolean) {
        isExpBalance = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = isExpBalance.edit()
        editor.putBoolean(IS_EXP_BAL, iss)
        editor.commit()
    }

    @JvmStatic
    fun getIsExpBal(context: Context): Boolean {
        isExpBalance = PreferenceManager.getDefaultSharedPreferences(context)
        return isExpBalance.getBoolean(IS_EXP_BAL, true)
    }




    // pref is Login
    @JvmStatic
    fun setIsLogin(context: Context, iss: Boolean) {
        isLogin = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = isLogin.edit()
        editor.putBoolean(IS_LOGIN, iss)
        editor.commit()
    }

    @JvmStatic
    fun getIsLogin(context: Context): Boolean {
        isLogin = PreferenceManager.getDefaultSharedPreferences(context)
        return isLogin.getBoolean(IS_LOGIN, false)
    }




    // pref UserId
    @JvmStatic
    fun setUserId(context: Context, `is`: String) {
        userId = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = userId.edit()
        editor.putString(USER_ID, `is`)
        editor.commit()
    }

    @JvmStatic
    fun getUserId(context: Context?): String? {
        userId = PreferenceManager.getDefaultSharedPreferences(context)
        return userId.getString(USER_ID, "")
    }


//    ParentId
    @JvmStatic
    fun setParentId(context: Context, `is`: String) {
        parentId = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = parentId.edit()
        editor.putString(PARENT_ID, `is`)
        editor.commit()
    }

    fun getParentId(context: Context?): String? {
        parentId = PreferenceManager.getDefaultSharedPreferences(context)
        return parentId.getString(PARENT_ID, "")
    }

    @JvmStatic
    fun setUserType(context: Context, `is`: String) {
        userType = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = userType.edit()
        editor.putString(USER_TYPE, `is`)
        editor.commit()
    }

    fun getUserType(context: Context?): String? {
        userType = PreferenceManager.getDefaultSharedPreferences(context)
        return userType.getString(USER_TYPE, "")
    }


    // Token
    @JvmStatic
    fun setToken(context: Context, `is`: String) {
        token = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = token.edit()
        editor.putString(TOKEN, `is`)
        editor.commit()
    }
    @JvmStatic
    fun getToken(context: Context?): String? {
        token = PreferenceManager.getDefaultSharedPreferences(context)
        return token.getString(TOKEN, "")
    }

    // Name
    @JvmStatic
    fun setName(context: Context, `is`: String) {
        name = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = name.edit()
        editor.putString(NAME, `is`)
        editor.commit()
    }

    fun getName(context: Context?): String? {
        name = PreferenceManager.getDefaultSharedPreferences(context)
        return name.getString(NAME, "")
    }
    // pref User Name
    @JvmStatic
    fun setUserName(context: Context, `is`: String) {
        userName = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = userName.edit()
        editor.putString(USER_NAME, `is`)
        editor.commit()
    }

    fun getUserName(context: Context?): String? {
        userName = PreferenceManager.getDefaultSharedPreferences(context)
        return userName.getString(USER_NAME, "")
    }

    // pref User Mobile
    fun setUserMobile(context: Context, `is`: String) {
        userMobile = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = userMobile.edit()
        editor.putString(USER_MOBILE, `is`)
        editor.commit()
    }

    fun getUserAddress(context: Context): String? {
        userMobile = PreferenceManager.getDefaultSharedPreferences(context)
        return userMobile.getString(USER_MOBILE, "")
    }

    fun setUserAddress(context: Context, `is`: String) {
        userAddress = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = userAddress.edit()
        editor.putString(USER_ADDRESS, `is`)
        editor.commit()
    }

    fun getUserMobile(context: Context): String? {
        userMobile = PreferenceManager.getDefaultSharedPreferences(context)
        return userMobile.getString(USER_MOBILE, "")
    }

    // pref User Email ID
    fun setUserEmailId(context: Context, `is`: String) {
        userEmailId = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = userEmailId.edit()
        editor.putString(USER_EMAIL_ID, `is`)
        editor.commit()
    }

    fun getUserEmailId(context: Context): String? {
        userEmailId = PreferenceManager.getDefaultSharedPreferences(context)
        return userEmailId.getString(USER_EMAIL_ID, "")
    }

    // pref Image
    fun setUserImage(context: Context, `is`: String) {
        userImage = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = userImage.edit()
        editor.putString(USER_IMAGE, `is`)
        editor.commit()
    }

    fun getUserImage(context: Context): String? {
        userImage = PreferenceManager.getDefaultSharedPreferences(context)
        return userImage.getString(USER_IMAGE, "")
    }



}