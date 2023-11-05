package com.example.rokomaribookapp.models

import androidx.room.Embedded
import androidx.room.Relation

data class ProductsWithCategory(
    @Embedded
    val category: ProductCategory,
    @Relation(
        parentColumn = "id",
        entityColumn = "categoryId"
    )
    val products: List<Products>

)
