package com.example.testing_app.DataClass
import kotlinx.serialization.Serializable

@Serializable
data class RatioOfElementEquality(
    var id : Int,
    var id_element1 : Int,
    var id_element2 : Int
)