package com.example.testing_app.DataClass
import kotlinx.serialization.Serializable

@Serializable
data class ElementOfArrangement(
    var id: Int,
    var id_question: Int,
    var name: String,
    var position: Int
)

