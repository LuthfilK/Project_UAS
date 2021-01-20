package com.example.project_uas.catatan.room

import androidx.room.*


@Dao
interface CatatDao {
    @Insert
    suspend fun addCatat(catat: Catat)

    @Query("SELECT * FROM catat ORDER BY id DESC")
    suspend fun getCatats() : List<Catat>

    @Query("SELECT * FROM catat WHERE id=:catat_id")
    suspend fun getCatat(catat_id: Int) : List<Catat>

    @Update
    suspend fun updateCatat(catat: Catat)

    @Delete
    suspend fun deleteCatat(catat: Catat)
}