package com.example.spaceflightapi.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AgenciesList(
        val id : String,
        val type : String,
        val country_code : String,
        val name : String,
        val image_url : String
) : Parcelable





