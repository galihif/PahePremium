package com.giftech.filmku.core.domain.model

data class Movie(
    val id:Int,
    val title:String,
    val description:String,
    val poster:String,
    var backdrop:String="",
    val vote:Double,
    var language:String="",
    val genres:List<String>
)
