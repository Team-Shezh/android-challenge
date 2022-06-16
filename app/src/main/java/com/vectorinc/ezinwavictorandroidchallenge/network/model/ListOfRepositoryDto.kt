package com.vectorinc.ezinwavictorandroidchallenge.network.model

import com.google.gson.annotations.SerializedName

/**
 * A simple [ListOfRepositoryDto] class that's serializes the list of rest object from network
 */


class ListOfRepositoryDto(
    @SerializedName("created_at")
    var created_at: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("name")
    var namez: String? = null,

    @SerializedName("full_name")
    var full_name: String? = null
)