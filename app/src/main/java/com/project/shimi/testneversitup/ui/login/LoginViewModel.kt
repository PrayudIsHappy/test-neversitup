package com.project.shimi.testneversitup.ui.login

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.shimi.testneversitup.model.LoginBody
import com.project.shimi.testneversitup.model.LoginResponse
import com.project.shimi.testneversitup.repository.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel : ViewModel() {

    private var liveDataLogin: MutableLiveData<LoginResponse>? = null

    @SuppressLint("CheckResult")
    fun login(body: LoginBody): LiveData<LoginResponse> {
        liveDataLogin = MutableLiveData()
        ApiClient.create().login(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                liveDataLogin?.value = it
            }
        return liveDataLogin as MutableLiveData<LoginResponse>
    }
}
