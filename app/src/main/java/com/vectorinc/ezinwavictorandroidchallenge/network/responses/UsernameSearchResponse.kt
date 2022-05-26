package com.vectorinc.ezinwavictorandroidchallenge.network.responses

import com.google.gson.annotations.SerializedName
import com.vectorinc.ezinwavictorandroidchallenge.network.model.UsernameDto

class UsernameSearchResponse(
    @SerializedName("total_count")
    var total_count: Int? = null,

    @SerializedName("incomplete_results")
    var incomplete_results: Boolean? = null,

    @SerializedName("items")
    var items: List<UsernameDto>
)