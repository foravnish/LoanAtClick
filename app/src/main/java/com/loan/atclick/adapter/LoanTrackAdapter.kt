package com.loan.atclick.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.grofiesta.data.model.ApiResponseModels
import com.loan.atclick.R
import kotlinx.android.synthetic.main.item_loan_track.view.*

class LoanTrackAdapter(
    val mList: List<ApiResponseModels.LoanTrackResponse.TrackData>,
    var itemClick: (Int,String) -> Unit
) :
    RecyclerView.Adapter<LoanTrackAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyHolder {
        var v: View
        v =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_loan_track, parent, false)

        return MyHolder(v)
    }


    override fun getItemCount(): Int {
        if (mList == null)
            return 0
        return mList.size
    }

    override fun onBindViewHolder(holder: MyHolder, p1: Int) {
        holder.bindData(mList[p1])
    }

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bindData(mList: ApiResponseModels.LoanTrackResponse.TrackData) {

            itemView.apply {
                mList.apply {
                    txtBankName.text = "" + bank_name
                    txtCustomerCare.text = "" + customer_care_no
                    txtAppNo.text = "" + application_no
                    txtBankAppNo.text = "" + bank_application_no
                    txtAmt.text = "₹" + loan_amount
                    txtStatus.text = "" + status
                    txtRemark.text = "" + remarks
                    txtDate.text = "" + create_date

                }
            }
//

//
//            itemView.imgClose.setOnClickListener {
//                itemClick(adapterPosition,"close") }

        }
    }

}