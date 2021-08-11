package ru.kavunov.mtsproject.bd

import androidx.room.*


@Dao
interface ProfilDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(profilTableModel: ProfilTableModel)

    @Update
    fun update(profilTableModel: ProfilTableModel)

    @Delete
    fun delete(profilTableModel: ProfilTableModel)

    @Query("SELECT * FROM ProfilModel WHERE profId == :id")
    fun getByName(id: Long): ProfilTableModel

    @Query("SELECT * FROM ProfilModel")
    fun get(): List<ProfilTableModel>

    @Transaction
    @Query("SELECT * FROM ProfilModel WHERE profId == :id")
    fun getProflistsWithCat(id: Long): List<ProfListWithCateg>
}
