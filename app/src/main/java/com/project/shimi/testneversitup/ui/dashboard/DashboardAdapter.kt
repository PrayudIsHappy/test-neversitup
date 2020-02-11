package com.project.shimi.testneversitup.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.shimi.testneversitup.R
import com.project.shimi.testneversitup.model.LoginResponse
import kotlinx.android.synthetic.main.adapter_dashboard.view.*
import kotlin.collections.ArrayList

class DashboardAdapter(
    private val listener: DashboardFragment
) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    var mCustomers: ArrayList<LoginResponse.Customer>? = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_dashboard,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = mCustomers?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(mCustomers?.get(position))
        holder.itemView.rootView.setOnClickListener {
            listener.onListClick(mCustomers?.get(position))
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(customer: LoginResponse.Customer?) {
            itemView.tvId.text = customer?.id
            itemView.tvName.text = customer?.name
        }
    }
}