package com.loan.atclick.ui.loan

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.app.grofiesta.data.model.ApiResponseModels
import com.loan.atclick.R
import com.loan.atclick.adapter.LoanStatusAdapter
import com.loan.atclick.adapter.LoanTrackAdapter
import com.loan.atclick.base.BaseActivity
import com.loan.atclick.login.LoanViewModel
import kotlinx.android.synthetic.main.activity_track.*
import kotlinx.android.synthetic.main.activity_track.noDataFond
import kotlinx.android.synthetic.main.app_header_layout.*

class LoanTrackActivity : BaseActivity() {
    lateinit var mViewModel: LoanViewModel
    var id=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track)


        mViewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(LoanViewModel::class.java)
        mViewModel.init(this)

        id=intent.getStringExtra("id")!!
        imgBack.setOnClickListener { finish() }
        txtPageTitle.text = "Track"

        getLoanTrack()


    }

    private fun getLoanTrack() {

        mViewModel.initGetMyAppStatusTrack(id,true)!!.observe(this, Observer {
            if (it.success) {

                if (it.track_data != null && it.track_data.size > 0) {
                    noDataFond.visibility= View.GONE
                    rvTrackList.visibility= View.VISIBLE
                    initAdapter(it.track_data)
                }
                else{
                    noDataFond.visibility= View.VISIBLE
                    rvTrackList.visibility= View.GONE
                }


            } else
                showToast(it.message)

        })




    }
    private fun initAdapter(mList: List<ApiResponseModels.LoanTrackResponse.TrackData>) {
        rvTrackList.layoutManager = GridLayoutManager(this,1)
        val mAdapter = LoanTrackAdapter(mList) { pos,type->
//            when(type){
//                "track"->  openTrack()
//                "detail"->  openDetail()
//            }
        }
        rvTrackList.adapter = mAdapter
    }



}