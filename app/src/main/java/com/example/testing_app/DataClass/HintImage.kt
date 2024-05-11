package com.example.testing_app.DataClass

import kotlinx.serialization.Serializable

@Serializable
data class HintImage(
    var id : Int,
    var id_hint: Int,
    var image: String?
)