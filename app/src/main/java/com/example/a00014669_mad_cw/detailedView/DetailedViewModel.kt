package com.example.a00014669_mad_cw.detailedView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a00014669_mad_cw.data.ParfumeRepository
import com.example.a00014669_mad_cw.data.dataClasses.Parfume
import kotlinx.coroutines.launch

class DetailedViewModel(
    parfumeId: String,
    private val parfumeRepository: ParfumeRepository
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

}