package com.example.testing_app.DataClass

import kotlinx.serialization.Serializable

@Serializable
data class HintText(
    var id : Int,
    var cost:Int,
    var text : String?,
    var hint_images: MutableList<HintImage>?,

    var inBasket:Boolean = false
)