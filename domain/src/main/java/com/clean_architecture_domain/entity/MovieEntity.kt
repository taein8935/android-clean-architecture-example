package com.clean_architecture_domain.entity


data class MovieEntity(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val category: String = "",
    val image: String = "",
    val backgroundUrl: String = ""
)
