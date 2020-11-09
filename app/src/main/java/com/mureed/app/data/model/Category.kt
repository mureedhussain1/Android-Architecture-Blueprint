package com.mureed.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String? = ""
)

data class CategoryData(
    var categories: List<Category>? = listOf()
)

data class CategoryResponse(
    var data: CategoryData?
)
