package br.com.raveline.myapplication.data.model


import com.google.gson.annotations.SerializedName

data class People(
    @SerializedName("people")
    val people: List<PeopleModel>
)