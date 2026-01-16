package com.example.posyandu.data.local.dao

import androidx.room.*
import com.example.posyandu.data.local.entity.Balita
import kotlinx.coroutines.flow.Flow

@Dao
interface BalitaDao {
    @Query("SELECT * FROM balita WHERE id_ibu = :idIbu")
    fun getBalitaByIbu(idIbu: Int): Flow<List<Balita>>

    @Insert
    suspend fun insertBalita(balita: Balita)

    @Update
    suspend fun updateBalita(balita: Balita)

    @Delete
    suspend fun deleteBalita(balita: Balita)

    @Query("SELECT * FROM balita WHERE nama_balita LIKE :query")
    suspend fun searchBalita(query: String): List<Balita>
}