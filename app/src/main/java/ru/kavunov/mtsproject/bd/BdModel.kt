package ru.kavunov.mtsproject.bd

import androidx.room.*


@Entity(tableName = "ProfilModel")
data class ProfilTable(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "profId")
    val profId: Long,
    val name: String,
    val email: String,
    val phone: String,
    val foto: String,
    )

@Entity(tableName = "CategoryModel")
data class CategoryTable(
    @PrimaryKey()
    @ColumnInfo(name = "categId")
    val categId: Long,
    val category: String,
)

@Entity(primaryKeys = ["profId", "categId"])
data class ProfilCategTable(
    val profId: Long,
    val categId: Long
)

@Entity(tableName = "MovieModel")
data class MovieTable(
    @PrimaryKey()
    @ColumnInfo(name = "movId")
    val movId: Long,
    val title: String,
    val description: String,

    val releaseDate: String,
    val rateScore: Float,
    val ageRestriction: String,
    val imageUrl: String,
    val genre: String,



    )

@Entity(tableName = "ActorModel")
data class ActorTable(
    @PrimaryKey()
    @ColumnInfo(name = "actId")
    val actId: Long,
    val imgAct: String,
    val nameAct: String,

)

@Entity(primaryKeys = ["movId", "actId"])
data class MovieActTable(
    val movId: Long,
    val actId: Long
)

data class MovListWithAct (
    val movId : Long? = null,
    val title: String? = null,

    @Relation(parentColumn = "movId",
        entityColumn = "actId",
        associateBy = Junction(MovieActTable::class))
    val listAct: List<ActorTable>? = null
)

data class ProfListWithCateg (
    val profId : Long? = null,
    val name: String? = null,

    @Relation(parentColumn = "profId",
        entityColumn = "categId",
        associateBy = Junction(ProfilCategTable::class))
    val listCat: List<CategoryTable>? = null
)

