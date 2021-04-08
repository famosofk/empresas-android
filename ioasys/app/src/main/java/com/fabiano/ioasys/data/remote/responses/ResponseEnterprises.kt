package com.fabiano.ioasys.data.remote.responses

import com.fabiano.ioasys.model.EnterpriseData

data class ResponseEnterprises(
    val enterprises: List<EnterpriseData>
)