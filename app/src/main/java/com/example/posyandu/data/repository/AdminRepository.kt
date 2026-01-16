package com.example.posyandu.data.repository

import com.example.posyandu.data.local.dao.AdminDao
import com.example.posyandu.data.local.entity.Admin

class AdminRepository(private val adminDao: AdminDao) {
    // REQ-REG-3: Menyimpan admin baru ke database
    suspend fun register(admin: Admin) = adminDao.register(admin)

    suspend fun hasAnyAdmin(): Boolean {
        return adminDao.getAdminCount() > 0
    }

    // REQ-LOG-2: Validasi kredensial login
    suspend fun login(username: String): Admin? = adminDao.login(username)
}
