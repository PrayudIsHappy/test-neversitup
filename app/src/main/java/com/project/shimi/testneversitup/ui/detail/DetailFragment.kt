package com.project.shimi.testneversitup.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.project.shimi.testneversitup.MainActivity
import com.project.shimi.testneversitup.R
import com.project.shimi.testneversitup.TestNeversitupApp
import com.project.shimi.testneversitup.model.GetCustomerDetailBody
import com.project.shimi.testneversitup.model.GetCustomerDetailResponse
import com.project.shimi.testneversitup.model.LoginResponse
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    companion object {
        private const val BUNDLE_CUSTOMER_DETAIL = "BUNDLE_CUSTOMER_DETAIL"

        fun newInstance(customer: LoginResponse.Customer?) = DetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(BUNDLE_CUSTOMER_DETAIL, customer)
            }
        }
    }

    private val customer: LoginResponse.Customer?
        get() = arguments?.getParcelable(
            BUNDLE_CUSTOMER_DETAIL
        )
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        callDetail()
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    private fun callDetail() {
        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        detailViewModel.getCustomerDetail(getGetCustomerDetailBody()).observe(this, Observer { it ->
            updateData(it.data)
        })
    }

    private fun getGetCustomerDetailBody(): GetCustomerDetailBody {
        val body = GetCustomerDetailBody()
        body.token = (activity?.application as TestNeversitupApp).getToken()
        body.customerId = customer?.id
        return body
    }

    private fun updateData(customerDetail: GetCustomerDetailResponse.Data?) {
        customerDetail?.let {
            tvId.text = customerDetail.id
            tvName.text = customerDetail.name
            tvGrade.text = customerDetail.customerGrade
            tvPremium.text = if (customerDetail.isCustomerPremium!!) "Yes" else "No"
            tvSex.text = customerDetail.sex
        }
    }
}
