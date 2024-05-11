package com.example.testing_app.DataClass
import kotlinx.serialization.Serializable

@Serializable
data class ElementOfGroup(
    var id: Int,
    var id_group: Int,
    var name: String,
    var ratio_numeri: Int,

    var user_ratio_numeri:Int = 0
)
