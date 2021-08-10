package ru.kavunov.mtsproject.bd

import androidx.room.*


@Entity(tableName = "ProfilModel")
data class ProfilModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "profId")
    val profId: Long,
    val name: String,
    val email: String,
    val phone: String,
    val foto: String,
    var interests1: String,
    var interests2: String,
    var interests3: String,
    )
//
@Entity(tableName = "CategoryModel")
data class CategoryModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "categId")
    val categId: Long,
    val category: String,
)

@Entity(primaryKeys = ["profId", "categId"])
data class ProfilCateg(
    val profId: Long,
    val categId: Long
)

data class ProfListWithCateg(
    @Embedded val playlist: ProfilModel,
    @Relation(
        parentColumn = "profId",
        entityColumn = "categId",
        associateBy = Junction(ProfilCateg::class)
    )
    val songs: List<CategoryModel>
)

