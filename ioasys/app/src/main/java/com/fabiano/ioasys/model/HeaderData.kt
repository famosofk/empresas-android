package com.fabiano.ioasys.model

import java.io.Serializable

data class HeaderData(
    val access_token: String = "",
    val client: String = "",
    val uid: String = "",
) : Serializable
