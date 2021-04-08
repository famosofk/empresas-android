package com.fabiano.ioasys.model

import com.google.gson.annotations.SerializedName

data class EnterpriseType(
    val id: Int = -1,
    @SerializedName("")
    val type: String = "enterprise_type_name"
)