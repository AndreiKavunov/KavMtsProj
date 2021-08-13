package ru.kavunov.mtsproject.bd

import androidx.room.*


@Dao
interface ProfilDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(profilTable: ProfilTable)

    @Update
    fun update(profilTable: ProfilTable)

    @Delete
    fun delete(profilTable: ProfilTable)

    @Query("SELECT * FROM ProfilModel WHERE profId == :id")
    fun getByName(id: Long): ProfilTable

    @Query("SELECT * FROM ProfilModel")
    fun get(): List<ProfilTable>

    @Transaction
    @Query("SELECT * FROM ProfilModel WHERE profId == :id")
    fun getProflistsWithCat(id: Long): List<ProfListWithCateg>
}
