package com.fabiano.ioasys.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fabiano.ioasys.data.remote.`interface`.EnterpriseAPI
import com.fabiano.ioasys.model.EnterpriseData
import com.fabiano.ioasys.model.HeaderData
import com.fabiano.ioasys.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class SearchViewModel : ViewModel() {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(EnterpriseAPI::class.java)

    private val _visibleList = MutableLiveData<List<EnterpriseData>>()
    val visibleList: LiveData<List<EnterpriseData>>
        get() = _visibleList

    private val _isCaching = MutableLiveData(false)
    val isCaching: LiveData<Boolean>
        get() = _isCaching
    private val _seedingError = MutableLiveData<String>()
    val seedingError: LiveData<String>
        get() = _seedingError

    private lateinit var enterpriseList: List<EnterpriseData>

    fun filterList(term: String) {
        if (!::enterpriseList.isInitialized) return
        if (term.isNotEmpty()) {
            _visibleList.value = enterpriseList.filter {
                it.enterprise_name.toLowerCase(Locale.getDefault())
                    .contains(term.toLowerCase(Locale.getDefault()))
            }
        } else {
            _visibleList.value = enterpriseList
        }
    }

    fun initilalizeList(headerData: HeaderData) {
        _isCaching.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val response = try {
                retrofit.getEnterpriseIndex(
                    headerData.access_token,
                    headerData.client,
                    headerData.uid
                )
            } catch (e: HttpException) {
                return@launch
            }
            if (response.isSuccessful) {
                enterpriseList = response.body()?.enterprises ?: listOf()
                _visibleList.postValue(enterpriseList)
            } else {
                _seedingError.postValue(response.message())
            }
            _isCaching.postValue(false)
        }
    }
}