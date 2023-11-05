package com.example.randomwomenapp.data.remote.dto
data class FemaleDto(
    val name: Name,
    val picture: Picture,
    val nat: String
)
data class Name(
    val title: String,
    val first: String,
    val last: String
)
data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)