package com.loan.atclick.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.grofiesta.data.model.ApiResponseModels
import com.bumptech.glide.Glide
import com.loan.atclick.R
import kotlinx.android.synthetic.main.item_loan_status.view.*

class LoanStatusAdapter(
    val mList: List<ApiResponseModels.MyAppStatusResponse.LoanData>,
    var itemClick: (Int,String) -> Unit
) :
    RecyclerView.Adapter<LoanStatusAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyHolder {
        var v: View
        v =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_loan_status, parent, false)

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


        fun bindData(mList: ApiResponseModels.MyAppStatusResponse.LoanData) {

            itemView.apply {
                mList.apply {
                    txtAppNumber.text = "" + application_no
                    txtAmt.text = "₹" + loan_amount
                    txtStatus.text = "" + status
                    txtDate.text = "" + apply_date
                }
            }
//
            itemView.txtTrack.setOnClickListener {
                itemClick(adapterPosition,"track")
            }

            itemView.txtDetail.setOnClickListener {
                itemClick(adapterPosition,"detail")
            }
//
//            itemView.imgClose.setOnClickListener {
//                itemClick(adapterPosition,"close") }

        }
    }

}