package com.example.testing_app.DataClass

import kotlinx.serialization.Serializable

@Serializable
data class AppSetting(
    var id: Int,
    var count_of_hints: Int,
    var time: String,
    var count_of_questions: Int,
    var date_of_changing: String,
    var raiting5: Int,
    var raiting4: Int,
    var raiting3: Int,
    var hint_visibility: Boolean,
    var result_visibiliry: Boolean,

    var user_count_hint:Int = 0
)














