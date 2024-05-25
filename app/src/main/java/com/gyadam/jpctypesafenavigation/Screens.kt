package com.gyadam.jpctypesafenavigation

import kotlinx.serialization.Serializable

@Serializable
object FirstScreen

@Serializable
data class SecondScreenModel(
    val name: String?,
    val randomNumber: Int,
)