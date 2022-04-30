package com.ahmetkanat.api.model

data class Response(
    val success : Boolean?,
    val city : String?,
    val result : List<Weather>?
)