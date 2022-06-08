package com.loan.atclick.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.grofiesta.data.model.ApiResponseModels
import com.bumptech.glide.Glide
import com.loan.atclick.R
import kotlinx.android.synthetic.main.item_dashboard_baner.view.*

class DashboardBannerAdapter(
    val mList: List<String>,
    var itemClick: (Int) -> Unit
) :
    RecyclerView.Adapter<DashboardBannerAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyHolder {
        var v: View
        v =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_dashboard_baner, parent, false)

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


        fun bindData(mList: String) {

            Glide.with(itemView.context).load(""+mList).into(itemView.imageView!!)

            itemView.imageView.setOnClickListener {
                itemClick(position)
            }

        }
    }

}