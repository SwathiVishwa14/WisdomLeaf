package com.example.wisdomleaf.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BookListNetworkResponse(
    @SerializedName("")
    @Expose
    val data:List<BookListNetworkResponseItem>
)
data class BookListNetworkResponseItem(
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("author")
    @Expose
    val author: String,
    @SerializedName("width")
    @Expose
    val width: Int,
    @SerializedName("height")
    @Expose
    val height: Int,
    @SerializedName("url")
    @Expose
    val url: String,
    @SerializedName("download_url")
    @Expose
    val downloadUrl: String
)