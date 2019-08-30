package com.nestedrecylcerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChildAdapter(val mContext:Context,val headerView:View,val rootPosition:Int, var list:List<String>,childClickListener: ChildClickListener) : RecyclerView.Adapter<ChildAdapter.OrderViewHolder>() {

    var childClickListener: ChildClickListener = childClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.child_item, parent, false))
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {


    }

    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val childImage by lazy { itemView.findViewById(R.id.child_image) as ImageView }

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(childView: View) {
            childClickListener.onItemClick(rootPosition,adapterPosition, headerView,childView)
        }
    }

    interface ChildClickListener {
        fun onItemClick(rootPoition:Int,childPosition:Int,headerView: View ,childView: View)
    }
}