package com.example.posyandu.data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.posyandu.data.local.entity.Admin
import com.example.posyandu.data.local.entity.Laporan

data class LaporanDetail(
    @Embedded val laporan: Laporan,
    @Relation(
        parentColumn = "id_admin",
        entityColumn = "id_admin"
    )
    val pembuat: Admin
)