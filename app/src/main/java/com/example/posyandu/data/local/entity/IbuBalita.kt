package com.example.posyandu.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ibu_balita")
data class IbuBalita(
    @PrimaryKey(autoGenerate = true)
    val id_ibu: Int = 0,
    val nama_ibu: String,
    val alamat: String,
    val no_telp: String
)