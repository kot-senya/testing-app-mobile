package com.example.testing_app.Storage

import android.content.Context
import android.util.Log
import com.example.testing_app.DataClass.AppSetting
import com.example.testing_app.DataClass.Question
import com.example.testing_app.DataClass.jsonQuestion
import com.example.testing_app.DataClass.jsonSettings
import kotlinx.serialization.json.Json

object ConverterFromJson {

    fun getQuestion(context: Context):Array<Question>?{
        var jsonText: String? = null
        var list: jsonQuestion? = null
        try {
            jsonText = getJsonDataFromAsset(context,"questions.json")
            if (jsonText == null){
                Log.i("INFO","getQuestion: jsonText is null")
                return null
            }
            list = Json{ ignoreUnknownKeys = true }.decodeFromString<jsonQuestion>(jsonText!!)
            return list.question
        }
        catch (ex: Exception)
        {
            Log.e("Error","getQuestion: " + ex.message.toString())
            return null
        }
    }

    fun getSetting(context: Context):AppSetting?{
        var jsonText: String? = null
        var list: jsonSettings? = null
        try {
            jsonText = getJsonDataFromAsset(context,"settings.json")
            if (jsonText == null){
                Log.i("INFO","getQuestion: jsonText is null")
                return null
            }
            list = Json{ ignoreUnknownKeys = true }.decodeFromString<jsonSettings>(jsonText.toString())
            return list.setting!![0]
        }
        catch (ex: Exception)
        {
            Log.e("Error","getQuestion: " + ex.message.toString())
            return null
        }
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ex: Exception) {
            Log.e("Error", "getJsonDataFromAsset: "+ex.message.toString())
            return null
        }
        return jsonString
    }
}