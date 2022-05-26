package com.vectorinc.ezinwavictorandroidchallenge.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Username(
    val login: String? = null,
    val public_repos: Int? = null,
    val avatar_url: String? = null,
    val name: String? = null,
    val type: String? = null

    ) : Parcelable