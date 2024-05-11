package com.example.testing_app.DataClass

import kotlinx.serialization.Serializable

@Serializable
data class jsonQuestion(var question: Array<Question>?)
