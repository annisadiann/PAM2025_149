package com.example.posyandu.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "jadwal_kontrol",
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
data class JadwalKontrol(
    @PrimaryKey(autoGenerate = true) val id_jadwal: Int = 0,
    val id_balita: Int,
    val id_admin: Int,
    val tanggal_kontrol: Date,
    val sudah_dihubungi: Boolean = false
)