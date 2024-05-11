package com.example.testing_app.DataClass
import kotlinx.serialization.Serializable

@Serializable
data class ElementOfEquality(
    var id: Int,
    var id_question: Int,
    var name: String,

    var ratio: MutableList<RatioOfElementEquality>
)
