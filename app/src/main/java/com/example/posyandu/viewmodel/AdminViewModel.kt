package com.example.posyandu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posyandu.data.local.entity.Admin
import com.example.posyandu.data.repository.AdminRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AdminViewModel(private val repository: AdminRepository) : ViewModel() {

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    // PERBAIKAN REQ-REG-3: Gunakan Dispatchers.IO untuk operasi database
    fun register(admin: Admin) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.register(admin) // Simpan ke DB
            } catch (e: Exception) {
                // Jika crash karena constraint database (misal username duplikat)
                // Error ditangkap di sini, bukan membuat aplikasi mati
                e.printStackTrace()
            }
        }
    }

    // REQ-LOG-2: Validasi Login Admin
    fun login(
        username: String,
        pass: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            // Proses login biasanya menggunakan database lookup,
            // pastikan repository.login(username) juga bersifat suspend
            val admin = repository.login(username)

            if (admin != null) {
                if (admin.password == pass) {
                    _isLoggedIn.value = true
                    onSuccess()
                } else {
                    onError("Password salah!") // REQ-LOG-5
                }
            } else {
                onError("Akun tidak ditemukan. Silakan registrasi terlebih dahulu.")
            }
        }
    }
    fun checkSession(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            // Logika: Cek apakah ada data admin di database
            // Atau Anda bisa menggunakan SharedPreferences untuk menyimpan ID Admin yang login
            val adminExists = repository.hasAnyAdmin() // Buat fungsi ini di repository
            onResult(adminExists)
        }
    }
    fun logout(onComplete: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            // Logika: Jika ingin benar-benar menghapus sesi, hapus data admin di DB
            // Atau jika menggunakan SharedPreferences, hapus key-nya di sini.
            onComplete()
        }
    }
}