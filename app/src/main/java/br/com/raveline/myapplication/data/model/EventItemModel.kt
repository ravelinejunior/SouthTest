package br.com.raveline.myapplication.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class EventItemModel(
    @SerializedName("cupons")
    val cupons: List<Any>?,
    @SerializedName("date")
    val date: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String?,
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?,
    @SerializedName("people")
    val people: List<PeopleModel>?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("title")
    val title: String?
) : Serializable