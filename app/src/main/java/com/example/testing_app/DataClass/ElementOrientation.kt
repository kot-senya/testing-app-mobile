package com.example.testing_app.DataClass

import kotlinx.serialization.Serializable

@Serializable
data class ElementOrientation(
    var id: Int,
    var name : String
)