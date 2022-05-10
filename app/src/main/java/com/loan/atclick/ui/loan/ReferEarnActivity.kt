package com.loan.atclick.ui.loan

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.accountapp.accounts.utils.Prefences
import com.app.grofiesta.data.model.ApiResponseModels
import com.loan.atclick.R
import com.loan.atclick.adapter.MyReferAdapter
import com.loan.atclick.adapter.TypeAdapter
import com.loan.atclick.base.BaseActivity
import com.loan.atclick.login.LoanViewModel
import com.loan.atclick.utils.Utility
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_refer_earn.*
import kotlinx.android.synthetic.main.app_header_layout.*
import kotlinx.android.synthetic.main.dialog_type.*

class ReferEarnActivity : BaseActivity() {

    lateinit var mViewModel: LoanViewModel
    lateinit var mLoanTypeList: ArrayList<String>
    val mContext by lazy { this }
    lateinit var  mDialog:Dialog
    var mLoanType = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refer_earn)

        mLoanTypeList = ArrayList()

        mViewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(LoanViewModel::class.java)
        mViewModel.init(this)

        imgBack.setOnClickListener { finish() }
        txtPageTitle.text = "Refer and Earn"

        getLoanType()

        getMyReferal()

        edtOnclick.setOnClickListener {
            settPopupLoanType(mLoanTypeList)
        }

    }

    private fun getMyReferal() {

        mViewModel.initGetMyReferal(true)!!.observe(this, Observer {
            hideKeyboard()
            if (it.success) {

                if (it.refer_data != null && it.refer_data.size > 0) {
                    txtHeading.visibility=View.VISIBLE
                    rvReferalList.visibility=View.VISIBLE
                    initAdapter(it.refer_data)
                }
                else{
                    txtHeading.visibility=View.GONE
                    rvReferalList.visibility=View.GONE
                }


            } else
                showToast(it.message)

        })

    }

    private fun getLoanType() {

        mViewModel.initGetLoanType(true)!!.observe(this, Observer {
            hideKeyboard()
            if (it.success) {

                it.loan_type.forEach {
                    mLoanTypeList.add(it)
                }


            } else
                showToast(it.message)

        })

    }

    private fun settPopupLoanType(mLoanTypeList: java.util.ArrayList<String>) {


        mDialog = Utility.MyCustomDialog(this@ReferEarnActivity, R.layout.dialog_type)
        mDialog.txtSelect.text = "Select Loan Type."

        mDialog.imgClose.setOnClickListener { mDialog.dismiss() }
        mDialog.rvTypeList.layoutManager = LinearLayoutManager(this)
        val mAdapter = TypeAdapter(mLoanTypeList) {
            mDialog.dismiss()
            mLoanType = mLoanTypeList[it]
            edtOnclick.setText(""+mLoanType)
        }
        mDialog.rvTypeList.adapter = mAdapter


        mDialog.show()


    }

    fun onCLickSubmit(view: View) {

        hideKeyboard()
        if (editName.text.toString().isEmpty())
            showAlert("Please enter name")
        else if (edtPhone.text.toString().isEmpty())
            showAlert("Please enter Phone")
        else if (edtAmount.text.toString().isEmpty())
            showAlert("Please enter Amount")
        else if (mLoanType.isEmpty())
            showAlert("Please select Loan Type")
        else {
            callSubmitReferalApi()

        }
    }

    private fun callSubmitReferalApi() {

        mViewModel.initSendReferl(
            editName.text.toString(), Prefences.getUserEmailId(this)!!,
            edtPhone.text.toString(), mLoanType, edtAmount.text.toString(), true
        )!!.observe(this, Observer {
            hideKeyboard()
            if (it.success) {

                getMyReferal()

            } else
                showToast(it.message)

        })

    }

    private fun initAdapter(mList: List<ApiResponseModels.MyReferalResponse.ReferData>) {
        rvReferalList.layoutManager = GridLayoutManager(this, 1)
        val mAdapter = MyReferAdapter(mList) { pos, type ->
//            when(type){
//                "track"->  openTrack()
//                "detail"->  openDetail()
//            }
        }
        rvReferalList.adapter = mAdapter
    }


}