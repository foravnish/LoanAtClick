package com.loan.atclick.ui.loan

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.app.grofiesta.data.model.ApiResponseModels
import com.loan.atclick.R
import com.loan.atclick.adapter.LoanStatusAdapter
import com.loan.atclick.base.BaseActivity
import com.loan.atclick.login.LoanViewModel
import com.loan.atclick.utils.Utility
import kotlinx.android.synthetic.main.activity_app_status.*
import kotlinx.android.synthetic.main.activity_refer_earn.*
import kotlinx.android.synthetic.main.app_header_layout.*

class MyAppStatusActivity : BaseActivity() {
    lateinit var mViewModel: LoanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_status)
        mViewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(LoanViewModel::class.java)
        mViewModel.init(this)

        imgBack.setOnClickListener { finish() }
        txtPageTitle.text = "My Application Status"

        getMyStatusList()

    }

    private fun getMyStatusList() {

        mViewModel.initGetMyAppStatus(true)!!.observe(this, Observer {
            if (it.success) {

                if (it.loan_data != null && it.loan_data.size > 0) {
                    noDataFond.visibility=View.GONE
                    rvProductList.visibility=View.VISIBLE
                    initAdapter(it.loan_data)
                }
                else{
                    noDataFond.visibility=View.VISIBLE
                    rvProductList.visibility=View.GONE
                }


            } else
                showToast(it.message)

        })




    }


    private fun initAdapter(mList: List<ApiResponseModels.MyAppStatusResponse.LoanData>) {
        rvProductList.layoutManager = GridLayoutManager(this,1)
        val mAdapter = LoanStatusAdapter(mList) { pos,type->
            when(type){
                "track"->  openTrack(mList[pos].id)
                "detail"->  openDetail(mList[pos].id)
            }
        }
        rvProductList.adapter = mAdapter
    }

    private fun openDetail(id:String) {

        Intent(this@MyAppStatusActivity, LoanDetailActivity::class.java).apply {
            putExtra("id",id)
        }.let {
            Utility.startActivityWithLeftToRightAnimation(this,it)
        }
    }

    private fun openTrack(id: String) {
        Intent(this@MyAppStatusActivity, LoanTrackActivity::class.java).apply {
            putExtra("id",id)
        }.let {
            Utility.startActivityWithLeftToRightAnimation(this,it)
        }

    }


}