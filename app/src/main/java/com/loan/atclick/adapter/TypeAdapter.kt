package com.loan.atclick.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.loan.atclick.R
import kotlinx.android.synthetic.main.item_type.view.*


@SuppressLint("SetTextI18n")
class TypeAdapter(
    var mList: List<String>,
    var itemClick: (Int) -> Unit
) :
    RecyclerView.Adapter<TypeAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyHolder {
        val v =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_type, parent, false)
        return MyHolder(v)
    }

    override fun onBindViewHolder(holder: TypeAdapter.MyHolder, p1: Int) {
        holder.bindData(mList[p1])
    }

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(mListItem: String) {

            itemView.apply {
                mListItem.apply {
                    txtItemName.text=""+mListItem

                }
            }

            itemView.lytMain.setOnClickListener { itemClick(adapterPosition) }
        }
    }


    override fun getItemCount(): Int {
        return mList.size
    }

}