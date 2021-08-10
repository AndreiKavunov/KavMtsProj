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
    )

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

@Entity(tableName = "MovieModel")
data class MovieModel(
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
data class ActorModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "actId")
    val actId: Long,
    val imgAct: String,
    val nameAct: String,

)

@Entity(primaryKeys = ["movId", "actId"])
data class MovieAct(
    val movId: Long,
    val actId: Long
)

data class MovListWithAct (
    val movId : Long? = null,
    val title: String? = null,

    @Relation(parentColumn = "movId",
        entityColumn = "actId",
        associateBy = Junction(MovieAct::class))
    val listAct: List<ActorModel>? = null
)

data class ProfListWithCateg (
    val profId : Long? = null,
    val name: String? = null,

    @Relation(parentColumn = "profId",
        entityColumn = "categId",
        associateBy = Junction(ProfilCateg::class))
    val listCat: List<CategoryModel>? = null
)

