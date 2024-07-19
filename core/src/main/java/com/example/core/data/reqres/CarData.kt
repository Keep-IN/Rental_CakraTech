package com.example.core.data.reqres


import com.google.gson.annotations.SerializedName

data class CarData(
    @SerializedName("metadata")
    val metadata: Metadata,
    @SerializedName("record")
    val record: Record
)