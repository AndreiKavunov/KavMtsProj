package ru.kavunov.mtsproject.bd

import androidx.room.*


@Dao
interface ProfilCategDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(profilCategTable: ProfilCategTable)

    @Update
    fun update(profilCategTable: ProfilCategTable)

    @Delete
    fun delete(profilCategTable: ProfilCategTable)

    @Query("SELECT * FROM ProfilCategTable WHERE categId == :id")
    fun getByName(id: Long): ProfilCategTable

    @Query("SELECT * FROM ProfilCategTable")
    fun get(): List<ProfilCategTable>


}