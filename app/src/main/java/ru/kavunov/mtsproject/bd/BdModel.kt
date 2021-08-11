package ru.kavunov.mtsproject.bd

import androidx.room.*


@Entity(tableName = "ProfilModel")
data class ProfilTableModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "profId")
    val profId: Long,
    val name: String,
    val email: String,
    val phone: String,
    val foto: String,
    )

@Entity(tableName = "CategoryModel")
data class CategoryTableModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "categId")
    val categId: Long,
    val category: String,
)

@Entity(primaryKeys = ["profId", "categId"])
data class ProfilCategTableModel(
    val profId: Long,
    val categId: Long
)

@Entity(tableName = "MovieModel")
data class MovieTableModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "movId")
    val movId: Long,
    val title: String,
    val description: String,
    val rateScore: Int,
    val ageRestriction: String,
    val imageUrl: String,
)

@Entity(tableName = "ActorModel")
data class ActorTableModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "actId")
    val actId: Long,
    val imgAct: String,
    val nameAct: String,

)

@Entity(primaryKeys = ["movId", "actId"])
data class MovieActTableModel(
    val movId: Long,
    val actId: Long
)

data class MovListWithAct (
    val movId : Long? = null,
    val title: String? = null,

    @Relation(parentColumn = "movId",
        entityColumn = "actId",
        associateBy = Junction(MovieActTableModel::class))
    val listAct: List<ActorTableModel>? = null
)

data class ProfListWithCateg (
    val profId : Long? = null,
    val name: String? = null,

    @Relation(parentColumn = "profId",
        entityColumn = "categId",
        associateBy = Junction(ProfilCategTableModel::class))
    val listCat: List<CategoryTableModel>? = null
)

