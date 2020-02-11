package com.project.shimi.testneversitup.repository

import com.project.shimi.testneversitup.model.GetCustomerDetailBody
import com.project.shimi.testneversitup.model.GetCustomerDetailResponse
import com.project.shimi.testneversitup.model.LoginBody
import com.project.shimi.testneversitup.model.LoginResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiClient {

    @POST("login")
    fun login(@Body form:LoginBody): Observable<LoginResponse>

    @POST("getCustomerDetail")
    fun getCustomerDetail(@Body form: GetCustomerDetailBody): Observable<GetCustomerDetailResponse>

    companion object {
        fun create(): ApiClient {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://neversitup.pythonanywhere.com/")
                .build()
            return retrofit.create(ApiClient::class.java)
        }
    }
}
