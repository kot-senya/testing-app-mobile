package com.example.testing_app.DataClass

import kotlinx.serialization.Serializable

@Serializable
data class matchTheValue(
    var elem1: ElementOfEquality?,
    var elem2:ElementOfEquality?
)
