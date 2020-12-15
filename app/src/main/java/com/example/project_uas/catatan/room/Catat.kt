package com.example.project_uas.catatan.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Catat(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val title: String,
    val catat: String
)