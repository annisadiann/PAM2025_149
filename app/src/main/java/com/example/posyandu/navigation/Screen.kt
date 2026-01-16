package com.example.posyandu.navigation

sealed class Screen(val route: String) {
    // Auth & Start
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Register : Screen("register")

    // Main Menu / Dashboard
    object Dashboard : Screen("dashboard")

    // Fitur Layanan Klinis (Gabungan Pemeriksaan & Kontrol)
    object LayananKlinis : Screen("layanan_klinis")

    // Fitur Mengelola Data Ibu/Balita (REQ-IBU)
    object IbuBalita : Screen("ibu_balita")

    // Rute Tambah & Edit Ibu & Balita
    object AddEditIbu : Screen("add_edit_ibu") {
        fun createRoute(ibuId: Int) = "add_edit_ibu?ibuId=$ibuId"
    }

    // Fitur Mengelola Pemeriksaan
    object Pemeriksaan : Screen("pemeriksaan")

    // Fitur Detail Pemeriksaan (Hasil Hitung Gizi)
    object DetailPemeriksaan : Screen("detail_pemeriksaan/{pemeriksaanId}") {
        fun createRoute(pemeriksaanId: Int) = "detail_pemeriksaan/$pemeriksaanId"
    }

    // Fitur Jadwal Kontrol
    object JadwalKontrol : Screen("jadwal_kontrol")

    // Fitur Mengelola Laporan (REQ-LAP)
    object Laporan : Screen("laporan")
    object DetailLaporan : Screen("detail_laporan/{laporanId}") {
        fun createRoute(laporanId: Int) = "detail_laporan/$laporanId"
    }
}