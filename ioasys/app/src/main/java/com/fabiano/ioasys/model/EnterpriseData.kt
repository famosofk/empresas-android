package com.fabiano.ioasys.model

import java.io.Serializable

data class EnterpriseData(
    val city: String,
    val country: String,
    val description: String,
    val email_enterprise: Any,
    val enterprise_name: String,
    val enterprise_type: EnterpriseTypeX,
    val facebook: Any,
    val id: Int,
    val linkedin: Any,
    val own_enterprise: Boolean,
    val phone: Any,
    val photo: String,
    val share_price: Double,
    val twitter: Any,
    val value: Int
) : Serializable