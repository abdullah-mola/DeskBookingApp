package com.example.deskbookingappllication.model

import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("id")
    val comment_id: String,
    @SerializedName("commentedAt")
    val commented_at: String,
    val comment: String
)
