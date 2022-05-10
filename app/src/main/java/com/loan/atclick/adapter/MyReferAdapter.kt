package com.loan.atclick.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.grofiesta.data.model.ApiResponseModels
import com.bumptech.glide.Glide
import com.loan.atclick.R
import kotlinx.android.synthetic.main.item_my_refer.view.*

class MyReferAdapter(
     val mList: List<ApiResponseModels.MyReferalResponse.ReferData>,
    var itemClick: (Int,String) -> Unit
) :
    RecyclerView.Adapter<MyReferAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyHolder {
        var v: View
        v =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_my_refer, parent, false)

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


        fun bindData(mList: ApiResponseModels.MyReferalResponse.ReferData) {

            itemView.apply {
                mList.apply {
                    txtType.text = "" + loan_type
                    txtName.text = "" + name
                    refCode.text = "Code: " + ref_code
                    txtAmt.text = "₹" + amount
                    txtCity.text = ""+ city
                    txtPhone.text = "" + ph
                }
            }

//            itemView.imgClose.setOnClickListener {
//                itemClick(adapterPosition,"close") }

        }
    }

}