package com.example.testing_app.DataClass
import kotlinx.serialization.Serializable

@Serializable
data class ElementOfChoose(
    var id: Int,
    var id_question: Int,
    var name: String,
    var correctly: Boolean,

    var userCorrectly:Boolean = false
)