package com.example.a00014669_mad_cw.detailedView

import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a00014669_mad_cw.MainActivity
import com.example.a00014669_mad_cw.data.ParfumeRepository
import com.example.a00014669_mad_cw.data.dataClasses.Parfume
import kotlinx.coroutines.launch

class DetailedViewModel(
    parfumeId: String,
    private val parfumeRepository: ParfumeRepository,
    private val onNavigateAllProducts: () -> Unit
) : ViewModel() {

    val parfumeLiveData: MutableLiveData<Parfume> by lazy {
        MutableLiveData<Parfume>()
    }

    init {
        getParfumeById(parfumeId)
    }

    private fun getParfumeById(parfumeId: String) {
        viewModelScope.launch {
            if (!parfumeId.isNullOrEmpty()) {
                val parfume = parfumeRepository.getParfumeById(parfumeId)
                parfumeLiveData.value = parfume
            }
        }
    }


    fun deleteParfumeById(parfumeId: String) {
        viewModelScope.launch {
            try {

                val deleted = parfumeRepository.deleteParfumeById(parfumeId)

                Log.d("deleted_response", deleted.toString())
                onNavigateAllProducts()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}