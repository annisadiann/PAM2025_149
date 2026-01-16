package com.example.posyandu.data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.posyandu.data.local.entity.Balita
import com.example.posyandu.data.local.entity.IbuBalita

data class BalitaWithIbu(
    @Embedded val balita: Balita,
    @Relation(
        parentColumn = "id_ibu",
        entityColumn = "id_ibu"
    )
    val ibu: IbuBalita
)