package com.example.a00014669_mad_cw.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a00014669_mad_cw.data.ParfumeRepository
import com.example.a00014669_mad_cw.data.dataClasses.Parfume
import kotlinx.coroutines.launch

class ParfumeListViewModel(
    private val parfumeRepository: ParfumeRepository
) : ViewModel() {

    val parfumesLiveData: MutableLiveData<List<Parfume>> by lazy {
        MutableLiveData<List<Parfume>>()
    }

    init {
        getAllParfumes()
    }

    fun getAllParfumes() {
        viewModelScope.launch {
            val parfumes = parfumeRepository.getParfumeList()
            parfumesLiveData.value = parfumes
        }
    }
}