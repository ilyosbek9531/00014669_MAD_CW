package com.example.a00014669_mad_cw.addNew

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a00014669_mad_cw.data.ParfumeRepository
import com.example.a00014669_mad_cw.data.dataClasses.Parfume
import com.example.a00014669_mad_cw.data.network.MyResponse
import kotlinx.coroutines.launch

class AddNewParfumeViewModel(private val parfumeRepository: ParfumeRepository) : ViewModel() {

    val insertResponseLiveData: MutableLiveData<MyResponse> by lazy {
        MutableLiveData<MyResponse>()
    }

    fun saveNewParfume(parfume: Parfume) {
        viewModelScope.launch {
            try {

                val response = parfumeRepository.insertNewParfume(parfume)
                insertResponseLiveData.value = response

                Log.d("Update_response", response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}