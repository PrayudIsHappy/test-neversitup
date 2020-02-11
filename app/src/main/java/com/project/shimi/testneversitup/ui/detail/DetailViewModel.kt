package com.project.shimi.testneversitup.ui.detail

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.shimi.testneversitup.model.GetCustomerDetailBody
import com.project.shimi.testneversitup.model.GetCustomerDetailResponse
import com.project.shimi.testneversitup.repository.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailViewModel : ViewModel() {

    private var liveDataDetail: MutableLiveData<GetCustomerDetailResponse>? = null

    @SuppressLint("CheckResult")
    fun getCustomerDetail(body: GetCustomerDetailBody): LiveData<GetCustomerDetailResponse> {
        liveDataDetail = MutableLiveData()
        ApiClient.create().getCustomerDetail(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                liveDataDetail?.value = it
            }
        return liveDataDetail as MutableLiveData<GetCustomerDetailResponse>
    }
}
