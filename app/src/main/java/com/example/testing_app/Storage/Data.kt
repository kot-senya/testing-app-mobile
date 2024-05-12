package com.example.testing_app.Storage

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import com.example.testing_app.DataClass.AppSetting
import com.example.testing_app.DataClass.HintImage
import com.example.testing_app.DataClass.Question
import com.example.testing_app.DataClass.QuestionImage
import java.util.Base64
import java.util.Date

@SuppressLint("StaticFieldLeak")
object Data {
    var context: Context? = null
    var questions: MutableList<Question> = mutableListOf()
    var numerics: MutableList<Int> = mutableListOf()
    lateinit var appSetting: AppSetting
    var count: Int = 119
    var currentQuestion: Int = 0

    var width: Int = 0
    var dp: Int = 0

    lateinit var layoutInflater: LayoutInflater

    lateinit var dateStart: Date
    lateinit var dateEnd: Date

    var dialogMain: Dialog? = null
    var dialogUser: Dialog? = null

    var time: Long = 0

    fun setData() {
        if (context != null) {
            var result = ConverterFromJson.getQuestion(context!!)
            val setting = ConverterFromJson.getSetting(context!!)

            if (setting != null) appSetting = setting
            else
                appSetting = AppSetting(0, 15, "00:45:00", 30, "today", 30, 23, 16, true, true)

            count = appSetting.count_of_questions

            if (result != null) {
                result.shuffle()
                result = orgResult(result)
                questions = result.take(count).toMutableList()
            }

            numerics = mutableListOf()
            for (i in 1..count)
                numerics.add(i)

            val timeArray = appSetting.time.split(":")
            time = timeArray[0].toLong() * 360000 + timeArray[1].toLong() * 60000 + timeArray[2].toLong() * 1000
        }
    }

    fun checkAnswer():Boolean {
        try {
            for (i in 1..questions.size) {
                when (questions[i - 1].id_category) {
                    1, 2 -> {//выбор 1 варианта ответа
                        val list = questions[i - 1].elements_of_choose!!.filter { it.correctly }
                        var correctlyQuestion: Boolean = true
                        for (elem in list) {
                            if (!elem.userCorrectly)
                                correctlyQuestion = false
                        }
                        questions[i - 1].correctlyAnswer = correctlyQuestion
                    }

                    3, 4 -> {//перестановка элементов
                        val list = questions[i - 1].elements_of_arrangment!!
                        var correctlyQuestion: Boolean = true
                        for (index in 1..list.size) {
                            if (index != list[index - 1].position)
                                correctlyQuestion = false
                        }
                        questions[i - 1].correctlyAnswer = correctlyQuestion
                    }

                    5 -> {//соотношение величин
                        val list = questions[i - 1].matchingValues
                        var correctlyQuestion: Boolean = true
                        for (elem in list) {
                            if(elem.elem1 == null || elem.elem2 == null)
                                correctlyQuestion = false
                            if(elem.elem1 == null  && elem.elem2 == null) {
                                val var1 = elem.elem1!!.id == elem.elem2!!.ratio[0].id_element1
                                val var2 = elem.elem1!!.id == elem.elem2!!.ratio[0].id_element2
                                if (var1 == false && var2 == false)
                                    correctlyQuestion = false
                            }
                        }
                        if(list.size == 0) correctlyQuestion = false
                        questions[i - 1].correctlyAnswer = correctlyQuestion

                    }

                    6 -> {//соотношение группы элементов
                        var elems1 =
                            questions[i - 1].groups!![0].elements_of_group!!.sortedBy { it.user_ratio_numeri }
                        var elems2 =
                            questions[i - 1].groups!![1].elements_of_group!!.sortedBy { it.user_ratio_numeri }
                        var correctlyQuestion: Boolean = true

                        for (index in 1..elems1.size) {
                            if (elems1[index - 1].ratio_numeri != elems2[index - 1].ratio_numeri)
                                correctlyQuestion = false
                        }
                        questions[i - 1].correctlyAnswer = correctlyQuestion
                    }

                    7 -> {//подстановка ответов
                        val list = questions[i - 1].elements_of_putting!!
                        var correctlyQuestion: Boolean = true
                        for (elem in list) {
                            if (elem.elem ==null || elem.elem!!.correctly == false)
                                correctlyQuestion = false
                        }
                        questions[i - 1].correctlyAnswer = correctlyQuestion
                    }
                }
            }
            return true
        }catch (ex:Exception){
            Log.e("Error", "Data | checkAnswer: " + ex.message)
            return false
        }
    }

    fun convertImageLine(sourceList: MutableList<QuestionImage>): MutableList<ByteArray> {
        val list: MutableList<ByteArray> = mutableListOf()
        for (elem in sourceList) {
            val source = Base64.getDecoder().decode(elem.image!!.replace("\n", ""))
            list.add(source)
        }
        return list
    }

    fun convertImageFromHintLine(sourceList: MutableList<HintImage>): MutableList<ByteArray> {
        val list: MutableList<ByteArray> = mutableListOf()
        for (elem in sourceList) {
            val source = Base64.getDecoder().decode(elem.image!!.replace("\n", ""))
            list.add(source)
        }
        return list
    }

    private fun orgResult(list: Array<Question>): Array<Question> {
        for (i in 1..list.size) {
            if (list[i - 1].hints != null) {
                list[i - 1].hints = list[i - 1].hints!!.sortedBy { it.cost }.toMutableList()
            }

            when (list[i - 1].id_category) {
                1, 2 -> list[i - 1].elements_of_choose!!.shuffle()
                5 -> list[i - 1].elements_of_equality!!.shuffle()
                6 -> list[i - 1].groups!!.shuffle()
            }
        }
        return list
    }
}