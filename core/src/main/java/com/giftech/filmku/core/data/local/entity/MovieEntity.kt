package com.giftech.filmku.core.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NonNls

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id:Int,

    @ColumnInfo(name = "title")
    var title:String,

    @ColumnInfo(name = "description")
    var description:String,

    @ColumnInfo(name = "poster")
    var poster:String,

    @ColumnInfo(name = "backdrop")
    var backdrop:String="",

    @ColumnInfo(name = "vote")
    var vote:Double,

    @ColumnInfo(name = "language")
    var language:String="",

    @ColumnInfo(name = "genres")
    var genres:List<String>,

    @ColumnInfo(name = "length")
    var length:Int = 0
)
