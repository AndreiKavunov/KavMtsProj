package ru.kavunov.mtsproject.bd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ProfilModel(
    var name: String,
    var email: String,
    var phone: String,
    var foto: String,
    var interests1: String,
    var interests2: String,
    var interests3: String,

    )
{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null

}
