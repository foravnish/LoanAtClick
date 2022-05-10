package com.loan.atclick.base

import android.app.ProgressDialog
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.loan.atclick.R
import com.loan.atclick.utils.Utility


open abstract class BaseActivity : AppCompatActivity() {
    val TAG_MESSAGE = "OkHttp"
    private var mProgressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
//        initUI()

    }

    fun initLeftRightTransaction() {
        overridePendingTransition(R.anim.slide_in_right, R.anim.scale_down)
    }

    fun showDialog() {
        try {
            if (mProgressDialog != null && mProgressDialog!!.isShowing) {
                mProgressDialog!!.cancel()
            }
            mProgressDialog = Utility.showProgressDialog1(this)
        } catch (e: Exception) {
        }
    }

    fun dismissDialog() {
        try {
            if (mProgressDialog != null && mProgressDialog!!.isShowing) {
                mProgressDialog!!.cancel()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showToast(message: String) {
        if (message != null) {
            hideKeyboard()
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    open fun showAlert(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setMessage(message).setCancelable(false)
            .setPositiveButton("Ok") { dialog, id -> dialog.cancel() }
        val alert = alertDialogBuilder.create()
        alert.show()
    }


    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun setToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
    }

    fun setToolbarWithBackIcon(toolbar: Toolbar, title: String) {
        setSupportActionBar(toolbar)
        supportActionBar!!.title = title
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun setToolbarWithBackIconWithSubTitle(toolbar: Toolbar, title: String, subTitle: String) {
        setSupportActionBar(toolbar)
        supportActionBar!!.title = title
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.subtitle = subTitle
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }


    fun setToolbarWithBackIconSubTitle(toolbar: Toolbar, subTitle: String) {
        setSupportActionBar(toolbar)
        supportActionBar!!.subtitle = subTitle
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun setBackIcon(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun isInternetAvailable(parentLayout: View?, context: Context): Boolean {
        if (Utility.isNetworkAvailable(context))
            return true
        else {
            Utility.showSnackBar(parentLayout, "" + getString(R.string.err_check_interner))
            return false
        }
    }

    fun showLoadingView(
        show: Boolean,
        loadingView: ProgressBar,
        view: View
    ) {
        if (show) {
            loadingView.visibility = View.VISIBLE
            view.visibility = View.VISIBLE
        } else {
            loadingView.visibility = View.GONE
            view.visibility = View.GONE
        }

    }

    fun showServerErrorSnackbar(parentLayout: View?) {
        Utility.showSnackBar(parentLayout, "A server error has occurred")
    }

    fun showmssg(parentLayout: View?, msg: String) {
        Utility.showSnackBar(parentLayout, msg)
    }

    fun showToastmssg(context: Context, msg: String) {

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()

    }


//    override fun onResume() {
//        super.onResume()
//        Alerts.register(this)
//    }
//
//    override fun onPause() {
//        Alerts.unregister(this)
//        super.onPause()
//    }

}
