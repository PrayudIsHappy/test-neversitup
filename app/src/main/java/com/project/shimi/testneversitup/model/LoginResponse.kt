package com.project.shimi.testneversitup.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginResponse(
    val customers: ArrayList<Customer>?,
    val status: Int,
    val token: String?
) : Parcelable {
    @Parcelize
    data class Customer(
        val id: String?,
        val name: String?
    ) : Parcelable
}