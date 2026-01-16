package com.example.posyandu.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "standar_antropometri")
data class StandarAntropometri(
    @PrimaryKey(autoGenerate = true) val id_standar: Int = 0, // Kunci utama
    val umur_bulan: Int, // Umur acuan
    val jenis_kelamin: String, // Laki-laki/Perempuan
    val bb_min: Double, // Batas minimum berat
    val bb_max: Double, // Batas maksimum berat
    val tb_min: Double, // Batas minimum tinggi
    val tb_max: Double, // Batas maksimum tinggi
    val kategori: String // Deskripsi gizi (misal: "Tercukupi")
)
