package com.project.shimi.testneversitup.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.shimi.testneversitup.MainActivity
import com.project.shimi.testneversitup.R
import com.project.shimi.testneversitup.model.LoginResponse
import com.project.shimi.testneversitup.ui.detail.DetailFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*


interface DashboardFragmentListener {
    fun onListClick(customer: LoginResponse.Customer?)
}

class DashboardFragment : Fragment(), DashboardFragmentListener {

    companion object {
        private const val BUNDLE_CUSTOMER = "BUNDLE_CUSTOMER"

        fun newInstance(customers: ArrayList<LoginResponse.Customer>?) = DashboardFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList(BUNDLE_CUSTOMER, customers)
            }
        }
    }

    private val customers: ArrayList<LoginResponse.Customer>? get() = arguments?.getParcelableArrayList(BUNDLE_CUSTOMER)
    private val dashBoardAdapter = DashboardAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvCustomer.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
            adapter = dashBoardAdapter
        }
        dashBoardAdapter.apply {
            this.mCustomers = customers
        }.run {
            notifyDataSetChanged()
        }
        fabLogout.setOnClickListener {
            (activity as MainActivity).logout()
        }
    }

    override fun onListClick(customer: LoginResponse.Customer?) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, DetailFragment.newInstance(customer))
            ?.addToBackStack(null)
            ?.commit()
    }
}
