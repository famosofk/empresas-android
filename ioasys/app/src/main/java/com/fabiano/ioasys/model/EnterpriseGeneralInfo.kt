package com.fabiano.ioasys.model

import java.io.Serializable

data class EnterpriseGeneralInfo(
    val photo: String = "https://www.picsum.photos/600",
    val enterprise_name: String = "",
    val enterpriseType: EnterpriseType = EnterpriseType(),
    val country: String = "",
    val description: String = ""
): Serializable
