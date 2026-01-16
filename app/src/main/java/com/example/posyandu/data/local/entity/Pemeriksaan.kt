package com.example.posyandu.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "pemeriksaan",
    foreignKeys = [
        ForeignKey(
            entity = Balita::class,
            parentColumns = ["id_balita"],
            childColumns = ["id_balita"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Admin::class,
            parentColumns = ["id_admin"],
            childColumns = ["id_admin"]
        )
    ]
)
data class Pemeriksaan(
    @PrimaryKey(autoGenerate = true) val id_pemeriksaan: Int = 0,
    val id_balita: Int,
    val id_admin: Int,
    val tanggal_pemeriksaan: Date,
    val berat_badan: Double,
    val tinggi_badan: Double,
    val umur_balita: Int,
    val status_gizi: String
)