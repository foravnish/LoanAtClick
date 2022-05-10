package com.loan.atclick.ui.loan

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.loan.atclick.R
import com.loan.atclick.adapter.TypeAdapter
import com.loan.atclick.base.BaseActivity
import com.loan.atclick.login.LoanViewModel
import com.loan.atclick.utils.Utility
import kotlinx.android.synthetic.main.activity_app_form.*
import kotlinx.android.synthetic.main.activity_feedback.*
import kotlinx.android.synthetic.main.app_header_layout.*
import kotlinx.android.synthetic.main.dialog_type.*
import kotlinx.android.synthetic.main.lytform_eight.*
import kotlinx.android.synthetic.main.lytform_five.*
import kotlinx.android.synthetic.main.lytform_four.*
import kotlinx.android.synthetic.main.lytform_three.*
import kotlinx.android.synthetic.main.lytform_two.*

class ComplaintFeedbackActivity : BaseActivity() {

    lateinit var mViewModel: LoanViewModel
    lateinit var mDialog: Dialog
    var mComplaintTypeList = ArrayList<String>()
    var mTypeValue=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)
        mViewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(LoanViewModel::class.java)
        mViewModel.init(this)
        mComplaintTypeList.add("Feedback")
        mComplaintTypeList.add("Complaint")

        imgBack.setOnClickListener { finish() }
        txtPageTitle.text = "Feedback/Complaint Form"


        edtType.setOnClickListener {
            settPopupLoanType(mComplaintTypeList)
        }
        
    }

    fun onClickSubmit(view: View){
        hideKeyboard()
        if (mTypeValue.isEmpty())
            showAlert("Please select type")
        else if(edtSubject.text.toString().isEmpty()){
            showAlert("Please enter subject")
        }else if(edtMessage.text.toString().isEmpty()){
            showAlert("Please enter Message")
        } else
            sendComorFeedback()

    }

    private fun sendComorFeedback() {

        mViewModel.initAddCom(""+mTypeValue,
            ""+edtSubject.text.toString(),""+edtMessage.text.toString(),
            true)!!.observe(this, Observer {
            if (it.success) {
                showToast(""+it.message)
                finish()
            } else
                showToast(it.message)

        })

    }

    private fun settPopupLoanType(mList: java.util.ArrayList<String>) {


        mDialog = Utility.MyCustomDialog(this@ComplaintFeedbackActivity, R.layout.dialog_type)
        mDialog.txtSelect.text = "Select"

        mDialog.imgClose.setOnClickListener { mDialog.dismiss() }
        mDialog.rvTypeList.layoutManager = LinearLayoutManager(this)
        val mAdapter = TypeAdapter(mList) {
            mDialog.dismiss()

            mTypeValue = mList[it]
            edtType.setText("" + mTypeValue)
        }
        mDialog.rvTypeList.adapter = mAdapter


        mDialog.show()


    }


}