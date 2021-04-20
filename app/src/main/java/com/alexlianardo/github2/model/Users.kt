package com.alexlianardo.github2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Users(
    var username: String,
    var name: String,
    var company: String,
    var location: String,
    var photo: String,
    var repository: String,
    var followers: String,
    var following: String
): Parcelable
