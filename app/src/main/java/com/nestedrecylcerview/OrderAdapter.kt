package com.nestedrecylcerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OrderAdapter(val mContext:Context, var list:List<String>) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>(),ChildAdapter.ChildClickListener {



    lateinit var orderClickListener: OrderClickListener
    lateinit var orderChildListener: OrderChildListener
    private var childList:MutableList<String> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.header_item, parent, false))
    }
    init {
        for(i in 0..20){
            childList.add("$i")
        }
    }
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {

        holder.childRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL, false)
            adapter = ChildAdapter(mContext,holder.itemView,position,childList,this@OrderAdapter)
        }
    }

    fun setOnItemClickListener(orderClickListener: OrderClickListener) {
        this.orderClickListener = orderClickListener
    }
    fun setOnChildItemClickListener(orderChildListener: OrderChildListener) {
        this.orderChildListener = orderChildListener
    }
    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val headerText by lazy { itemView.findViewById(R.id.header_text) as TextView }
        val childRecycler by lazy { itemView.findViewById(R.id.child_recycler) as RecyclerView }


        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View) {
            orderClickListener.onItemClick(adapterPosition, v)
        }
    }

    interface OrderClickListener {
        fun onItemClick(position:Int, v: View)
    }
    interface OrderChildListener {
        fun onChildItemClick(headerPosition: Int,childPosition:Int, headerView: View,childView: View)
    }
    override fun onItemClick(rootPoition: Int, childPosition: Int, headerView: View, childView: View) {
        orderChildListener.onChildItemClick(rootPoition,childPosition,headerView,childView)
    }

}