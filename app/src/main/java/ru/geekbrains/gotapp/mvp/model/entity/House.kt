package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.Expose

@Parcelize
data class House(
    @Expose val name: String,
    @Expose val region: String,
    @Expose val words: String,
    @Expose val currentLord: String,
    @Expose val url: String
) : Parcelable