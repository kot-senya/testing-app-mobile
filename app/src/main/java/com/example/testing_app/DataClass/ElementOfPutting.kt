package com.example.testing_app.DataClass
import kotlinx.serialization.Serializable

@Serializable
data class ElementOfPutting(
    var id: Int,
    var id_text: Int,
    var name: String,
    var correctly: Boolean
)
