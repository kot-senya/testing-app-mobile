package com.example.testing_app.DataClass
import kotlinx.serialization.Serializable

@Serializable
data class QuestionImage(
    var id : Int,
    var id_question : Int,
    var image: String?
)