package com.example.pmdm_uf1_jestify_2.jokeAPI

import com.google.gson.annotations.SerializedName

data class Joke(
    val error: Boolean,
    val category: String?,
    val type: String?,
    val joke: String?,
    @SerializedName("setup") val setUp: String?, // Correct casing
    val delivery: String?,
    val flags: Map<String, Boolean>?,
    val id: Int?,
    val safe: Boolean?,
    val lang: String?
)
