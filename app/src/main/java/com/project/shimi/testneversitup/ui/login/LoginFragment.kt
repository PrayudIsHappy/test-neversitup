package com.project.shimi.testneversitup.ui.login

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
import com.project.shimi.testneversitup.model.LoginBody
import com.project.shimi.testneversitup.model.LoginResponse
import com.project.shimi.testneversitup.ui.dashboard.DashboardFragment
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var listViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        (activity as MainActivity).let { activity ->
            listViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
            listViewModel.login(getLoginBody()).observe(this, Observer {
                (activity.application as TestNeversitupApp).setToken(it.token)
                toDashboard(it.customers)
            })
        }
    }

    private fun getLoginBody(): LoginBody {
        val body = LoginBody()
        body.username = "test"
        body.password = "123456"
        return body
    }

    private fun toDashboard(customers: ArrayList<LoginResponse.Customer>?) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, DashboardFragment.newInstance(customers))
            ?.addToBackStack(null)
            ?.commit()
    }
}
