package com.example.randomwomenapp.data.remote.dto
data class FemaleDto(
    val name: Name,
    val nat: String
)

data class Name(
    val title: String,
    val first: String,
    val last: String
)
