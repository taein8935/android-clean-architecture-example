package com.clean_architecture_data.mapper

import com.clean_architecture_data.model.MovieDbData
import com.clean_architecture_domain.entity.MovieEntity

fun MovieEntity.toDbData() = MovieDbData(
    id = id,
    image = image,
    description = description,
    title = title,
    category = category,
    backgroundUrl = backgroundUrl
)
