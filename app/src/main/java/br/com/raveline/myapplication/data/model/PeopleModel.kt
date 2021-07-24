package br.com.raveline.myapplication.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PeopleModel(
    @SerializedName("eventId")
    val eventId: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("picture")
    val picture: String,

    val email:String
):Serializable