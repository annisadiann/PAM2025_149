package com.example.posyandu.utils

import com.example.posyandu.data.local.entity.StandarAntropometri

object AntropometriCalculator {

    /**
     * Menghitung status gizi berdasarkan data pemeriksaan dan tabel referensi standar.
     * Sesuai SRS: Perhitungan otomatis status gizi berdasarkan BB/TB[cite: 131].
     */
    fun hitungStatus(
        beratBadan: Double,
        tinggiBadan: Double,
        umurBulan: Int,
        jenisKelamin: String,
        daftarStandar: List<StandarAntropometri>
    ): String {
        // Mencari baris standar yang sesuai dengan umur dan jenis kelamin balita [cite: 291, 336]
        val standar = daftarStandar.find {
            it.umur_bulan == umurBulan && it.jenis_kelamin.equals(jenisKelamin, ignoreCase = true)
        }

        return if (standar != null) {
            // Validasi input sesuai standar WHO yang tersimpan di database [cite: 152, 297]
            if (beratBadan >= standar.bb_min && beratBadan <= standar.bb_max &&
                tinggiBadan >= standar.tb_min && tinggiBadan <= standar.tb_max) {
                "Tercukupi" // Status sehat sesuai kriteria gizi [cite: 133]
            } else {
                "Perlu Perhatian" // Identifikasi status kesehatan tidak tercukupi [cite: 133]
            }
        } else {
            "Data Standar Tidak Ditemukan"
        }
    }
}