package com.example.testing_app.DataClass

import kotlinx.serialization.Serializable

@Serializable
data class jsonSettings(var setting: Array<AppSetting>?)