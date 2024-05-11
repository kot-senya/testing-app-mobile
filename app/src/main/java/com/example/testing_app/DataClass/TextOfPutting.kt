package com.example.testing_app.DataClass
import kotlinx.serialization.Serializable

@Serializable
data class TextOfPutting(
    var id : Int,
    var id_question : Int,
    var name: String?,

    var elements: List<ElementOfPutting>?,

    var elem: ElementOfPutting? = null
)