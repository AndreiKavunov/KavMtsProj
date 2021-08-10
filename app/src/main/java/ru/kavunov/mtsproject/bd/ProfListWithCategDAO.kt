package ru.kavunov.mtsproject.bd

import androidx.room.Query
import androidx.room.Transaction

interface ProfListWithCategDAO {
    @Transaction
    @Query("SELECT * FROM ProfilModel")
    fun getPlaylistsWithSongs(): List<ProfListWithCateg>
}