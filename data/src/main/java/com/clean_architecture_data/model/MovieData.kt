package com.clean_architecture_data.model

import com.clean_architecture_domain.entity.MovieEntity
import com.google.gson.annotations.SerializedName

data class MovieData(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("category") val category: String,
    @SerializedName("image") val image: String,
    @SerializedName("backgroundUrl") val backgroundUrl: String,
)
fun MovieData.toDomain() = MovieEntity(
    id = id,
    title = title,
    description = description,
    category = category,
    image = image,
    backgroundUrl = backgroundUrl,
)