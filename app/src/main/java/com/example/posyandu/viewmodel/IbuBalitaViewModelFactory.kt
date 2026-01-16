package com.example.posyandu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.posyandu.data.repository.IbuBalitaRepository

class IbuBalitaViewModelFactory(private val repository: IbuBalitaRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IbuBalitaViewModel::class.java)) {
            return IbuBalitaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}