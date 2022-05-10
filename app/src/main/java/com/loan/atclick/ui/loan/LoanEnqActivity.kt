package com.loan.atclick.ui.loan

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.loan.atclick.R
import com.loan.atclick.base.BaseActivity
import com.loan.atclick.data.model.request.LoanEnq
import com.loan.atclick.login.LoanViewModel
import kotlinx.android.synthetic.main.activity_loan_enq.*
import kotlinx.android.synthetic.main.app_header_layout.*

class LoanEnqActivity : BaseActivity() {
    lateinit var mViewModel: LoanViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_enq)

        mViewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(LoanViewModel::class.java)
        mViewModel.init(this)
        imgBack.setOnClickListener { finish() }
        txtPageTitle.text = "Loan Enquiry"


    }

    fun onClickSubmit(view: View) {
        hideKeyboard()
        if (isValidate())
            sendLoanEnq()
    }

    private fun isValidate(): Boolean {

        if (editName.text.toString().isEmpty()) {
            showToast("Please enter name")
            return false
        }
        if (edtEmailId.text.toString().isEmpty()) {
            showToast("Please enter EmailId")
            return false
        }
        if (txtPhone.text.toString().isEmpty()) {
            showToast("Please enter Phone No")
            return false
        }
        if (edtLoanAmt.text.toString().isEmpty()) {
            showToast("Please enter Amount")
            return false
        }

        return true
    }


    private fun sendLoanEnq() {
        LoanEnq(
            "" + editName.text.toString(), "" + edtEmailId.text.toString(),
            "" + txtPhone.text.toString(), "",
            "" + edtMessage.text.toString(), "" + edtLoanAmt.text.toString(),
            "" + edtCompName.text.toString(), "" + edtCity.text.toString()
        ).let {
            mViewModel.initAddLoanEnq(
                it,
                true
            )!!.observe(this, Observer {
                if (it.success) {
                    showToast("" + it.message)
                    finish()
                } else
                    showToast(it.message)

            })

        }

    }

}