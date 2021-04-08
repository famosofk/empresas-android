package com.fabiano.ioasys.data.remote.`interface`

import com.fabiano.ioasys.data.remote.responses.LoginResponse
import com.fabiano.ioasys.data.remote.responses.ResponseEnterprises
import com.fabiano.ioasys.model.User
import retrofit2.Response
import retrofit2.http.*

interface EnterpriseAPI {

    @POST("users/auth/sign_in")
    suspend fun login(@Body user: User): Response<LoginResponse>

    @GET("enterprises")
    suspend fun getEnterpriseIndex(
        @Header("access-token") token: String,
        @Header("client") client: String,
        @Header("uid") uid: String,
    ): Response<ResponseEnterprises>

    @GET("enterprises")
    suspend fun getEnterpriseIndexFilter(
        @Header("access-token") token: String,
        @Header("client") client: String,
        @Header("uid") uid: String,
        @Query("enterprise_types") type: Int,
        @Query("name") name: String,
    ): Response<ResponseEnterprises>

}