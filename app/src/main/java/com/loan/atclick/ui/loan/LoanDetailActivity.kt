package com.loan.atclick.ui.loan

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.loan.atclick.R
import com.loan.atclick.base.BaseActivity
import com.loan.atclick.login.LoanViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.app_header_layout.*

class LoanDetailActivity : BaseActivity() {
    lateinit var mViewModel: LoanViewModel
    var id = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mViewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(LoanViewModel::class.java)
        mViewModel.init(this)

        id = intent.getStringExtra("id")!!
        imgBack.setOnClickListener { finish() }
        txtPageTitle.text = "Detail"

        getLoanDetail()


    }

    private fun getLoanDetail() {

        mViewModel.getMyAppStatusDetail(id, true)!!.observe(this, Observer {
            if (it.success) {

                if (it.loan_status_data != null) {
                    txtCustomerName.setText("" + it.loan_status_data.customer_name)
                    txtProducts.setText("" + it.loan_status_data.product)
                    txtLocation.setText("" + it.loan_status_data.location)
                    txtDisbursalDate.setText("" + it.loan_status_data.disbursal_date)
                    txtLoanAccountNo.setText("" + it.loan_status_data.loan_ac_no)
                    txtBankName.setText("" + it.loan_status_data.bank_name)
                    txtROI.setText("" + it.loan_status_data.roi)
                    txtProcessingFee.setText("" + it.loan_status_data.processing_fee)
                    txtMobileNo.setText("" + it.loan_status_data.mobile_no)
                    txtEmailId.setText("" + it.loan_status_data.email)
                    txtLoanAmount.setText("" + it.loan_status_data.loan_amount)
                    txtTenure.setText("" + it.loan_status_data.tenure)
                    txtEMIDate.setText("" + it.loan_status_data.emi_date)
                    txtDebitAccountNo.setText("" + it.loan_status_data.debit_ac_no)

                }

            } else
                showToast(it.message)

        })


    }


}