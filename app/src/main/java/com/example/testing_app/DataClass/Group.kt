package com.example.testing_app.DataClass

import kotlinx.serialization.Serializable

@Serializable
data class Group(
    var id: Int,
    var id_question: Int,
    var name : String,

    var elements_of_group: MutableList<ElementOfGroup>?
)