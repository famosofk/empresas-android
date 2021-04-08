package com.fabiano.ioasys.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fabiano.ioasys.data.remote.`interface`.EnterpriseAPI
import com.fabiano.ioasys.model.HeaderData
import com.fabiano.ioasys.model.User
import com.fabiano.ioasys.utils.Constants
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class LoginViewModel : ViewModel() {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(EnterpriseAPI::class.java)

    private val _successfulLogin = MutableLiveData(false)
    val sucessfulLogin: LiveData<Boolean>
        get() = _successfulLogin

    private val _inProgressLogin = MutableLiveData(false)
    val inProgressLogin: LiveData<Boolean>
        get() = _inProgressLogin
    private lateinit var acessToken: String
    private lateinit var client: String
    private lateinit var uid: String
    var loginError: String = ""
    fun provideHeaderData() = HeaderData(acessToken, client, uid)

    fun login(user: User) {
        _inProgressLogin.value = true
        loginError = ""
        CoroutineScope(Dispatchers.IO).launch {
            val response = try {
                retrofit.login(user)
            } catch (e: HttpException) {
                Log.e("Error: ", e.message())
                return@launch
            }
            if (response.isSuccessful) {
                acessToken = response.headers().get("access-token") ?: ""
                client = response.headers().get("client") ?: ""
                uid = response.headers().get("uid") ?: ""
                _successfulLogin.postValue(true)
            } else {
                loginError = response.message()
                _successfulLogin.postValue(false)
            }
            _inProgressLogin.postValue(false)
        }

    }

}