package com.example.testing_app.DataClass
import kotlinx.serialization.Serializable

@Serializable
data class Question(
    var id: Int,
    var name: String?,
    var id_category: Int,
    var price: Double,
    var in_test: Boolean?,

    var images: MutableList<QuestionImage>?,
    var hints: MutableList<HintText>?,

    var elements_of_arrangment: MutableList<ElementOfArrangement>?,
    var elements_of_choose: MutableList<ElementOfChoose>?,
    var elements_of_equality: MutableList<ElementOfEquality>?,
    var groups: MutableList<Group>?,
    var elements_of_putting: MutableList<TextOfPutting>?,

    var matchingValues: MutableList<matchTheValue> = mutableListOf(),
    var valuesForMatch: MutableList<String> = mutableListOf(),
    var correctlyAnswer:Boolean = false
)