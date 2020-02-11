package com.project.shimi.testneversitup.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GetCustomerDetailResponse(
    val data: Data?,
    val status: Int
) : Parcelable {
    @Parcelize
    data class Data(
        val customerGrade: String?,
        val id: String?,
        val isCustomerPremium: Boolean?,
        val name: String?,
        val sex: String?
    ) : Parcelable
}