package com.example.posyandu.data.repository

import androidx.lifecycle.LiveData
import com.example.posyandu.data.local.dao.LaporanDao
import com.example.posyandu.data.local.entity.Laporan
import kotlinx.coroutines.flow.Flow
import java.util.Date

class LaporanRepository(private val laporanDao: LaporanDao) {
    val allLaporan: Flow<List<Laporan>> = laporanDao.getAllLaporan()

    // REQ-LAP-4: Ambil data sesuai periode yang ditentukan Admin [cite: 251]
    suspend fun generateLaporan(start: Date, end: Date): List<Laporan> {
        return laporanDao.getLaporanByPeriode(start.time, end.time)
    }

    suspend fun simpanLaporan(laporan: Laporan) = laporanDao.simpanLaporan(laporan)
}