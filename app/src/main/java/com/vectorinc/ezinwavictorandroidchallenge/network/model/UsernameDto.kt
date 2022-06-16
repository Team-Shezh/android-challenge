package com.vectorinc.ezinwavictorandroidchallenge.network.model

import com.google.gson.annotations.SerializedName


class UsernameDto(
    @SerializedName("login")
    var login: String? = null,

    @SerializedName("avatar_url")
    var avatar_url: String? = null,

    @SerializedName("public_repos")
    var public_repos: Int? = null,


    @SerializedName("type")
    var type: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("bio")
    var bio: String? = null
)