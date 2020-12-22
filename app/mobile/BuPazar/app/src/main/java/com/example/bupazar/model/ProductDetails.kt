package com.example.bupazar.model

import com.google.gson.annotations.SerializedName

data class ProductDetails (
    @SerializedName("id") val productId: Long?,
    @SerializedName("name") val name: String?,
    @SerializedName("price") val price: Float?,
    @SerializedName("description") val description: String?,
    @SerializedName("stock") val stock: Long?,
    @SerializedName("number_of_sales") val numberOfSales: Long?,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("category") val category: String?,
    @SerializedName("rating") val rating: Float?
)